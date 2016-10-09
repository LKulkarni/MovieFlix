/*
 *  Code for Login Modal
 *
 */

// open modal
$(document).ready(function () {
    $("#signIn").click(function () {
        $("#loginModal").modal();
    });
});

// close modal
$(document).ready(function () {
    $("#loginSubmit").click(function () {
        $("#loginModal").modal("hide");
    });
});