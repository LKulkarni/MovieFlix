$(document).ready(function(){
    $("#signIn").click(function(){
        $("#loginModal").modal();
    });
});

$(document).ready(function(){
    $("#loginSubmit").click(function(){
        $("#loginModal").modal("hide");
    });
});