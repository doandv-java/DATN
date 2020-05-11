package haui.stores.services.impl;

import haui.stores.data.User;
import haui.stores.data.UserPrincipal;
import haui.stores.data.enums.ERole;
import haui.stores.data.enums.EStatus;
import haui.stores.dto.dxo.ChangePasswordDxo;
import haui.stores.dto.dxo.ForgotPasswordDxo;
import haui.stores.dto.dxo.RegisterDxo;
import haui.stores.framework.Constants;
import haui.stores.repositories.UserRepository;
import haui.stores.services.CommonService;
import haui.stores.services.ImageService;
import haui.stores.services.UserService;
import haui.stores.utils.GenratePassword;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CommonService commonService;

    @Override
    public boolean registerUser(RegisterDxo registerDxo) {
        boolean existUser = existUser(registerDxo.getUserName(), StringUtils.EMPTY);
        if (existUser) {
            log.error("{} exist in the DB", registerDxo.getUserName());
            return false;
        } else {
            User user = new User();
            user.setUserName(registerDxo.getUserName());
            log.info("{} has password:{}", registerDxo.getUserName(), registerDxo.getPassword());
            user.setPassword(passwordEncoder.encode(registerDxo.getPassword()));
            user.setName(registerDxo.getName());
            user.setRole(ERole.USER.getRole());
            user.setStatus(EStatus.ACTIVE.getCode());
            user.setAvatar(Constants.IMAGE_DEFAULT.USER);
            userDao.save(user);
            return true;
        }
    }

    @Override
    public boolean forgotPassword(ForgotPasswordDxo dxo) {
        User user = userDao.findUserByUserName(dxo.getUserName());
        if (user == null) {
            log.error("user {} not exist", dxo.getUserName());
            return false;
        } else {
            //Forgot password
            String password = GenratePassword.generatePassword();
            log.info("{} forgot password with new pass:{}", user.getUserName(), password);
            //send mail password
            user.setPassword(passwordEncoder.encode(password));
            userDao.save(user);
            return true;
        }
    }

    @Override
    public boolean changePassword(ChangePasswordDxo dxo) {
        User user = commonService.loadUserDetail();
        if (user == null) {
            log.error("No user login to change password!");
            return false;
        } else {
            //check user match
            if (passwordEncoder.matches(dxo.getPasswordOld(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(dxo.getPasswordNew()));
                log.info("{} change password {} success", user.getUserName(), dxo.getPasswordNew());
                userDao.save(user);
                return true;
            } else {
                log.error("{} change password fail {} not match pass", user.getUserName(), dxo.getPasswordOld());
                return false;
            }
        }
    }

    @Override
    public UserPrincipal loadUserByUsername(String userName) throws UsernameNotFoundException {
        //find user active
        User user = userDao.findUserByUserNameAndStatusIs(userName, EStatus.ACTIVE.getCode());
        if (user == null) {
            log.error("Unknown User");
            throw new UsernameNotFoundException("Unknown User");
        }
        //Setting auth
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new UserPrincipal(
                user, true, true, true, true,
                grantedAuthorities);
    }

    @Override
    public boolean existUser(String userName, String userNameOld) {
        User user = userDao.findUserByUserName(userName);
        if (StringUtils.isEmpty(userNameOld)) {
            return user != null;
        } else {
            boolean check = user == null || userName.equals(userNameOld);
            return !check;
        }
    }
}

