$(document).ready(function () {
    $('#supplyTable').dataTable();
});

function deleteSupply(id) {
    $.ajax({
        type: 'DELETE',
        url: "/admin/supply/" + id,
        success: function (data) {
            if (data.status) {
                window.location.href = window.origin + "/admin/supply";
            } else {
                console.log(data);
            }
        }
    });
}
