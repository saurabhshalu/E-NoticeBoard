$(document).ready(function() {
    $('.btnLogin').on('click',function() {
        $('body').load('./?mode=login');
        //window.location.href = "./?mode=login";
    });
    $('.btnSignUp').on('click',function() {
        $('body').load('./?mode=register');
        //window.location.href = "./?mode=register";
    });

    $('#btnSignIn').on('click', function() { 
        var username = $('#username').val();
        var password = $('#password').val();
        var logintype = $("input[name='logintype']:checked").val();
        if(username.trim()==="")
            showError("Username is required");
        else if(password.trim()==="")
            showError("Password is required");
        else {  
            $.ajax({
                url: 'login',
                method: 'POST',
                data: {
                        username: username,
                        password: password,
                        logintype: logintype
                },
                success: function(response)
                {
                  if(response==="success")
                      window.location.href = "./dashboard";
                  else
                      showError(response);
                }
            });
        }
    });
    $('#btnRegister').on('click', function() { 
        var registertype = $("input[name='registertype']:checked").val();
        
        var username = $('#username').val();
        var fullname = $('#fullname').val();
        var password = $('#password').val();
        var password2 = $('#password2').val();
        var branch = $('#branch').val();
        var semester = $('#semester').val();        
        var collegecode = $('#college').val();        
        var phone = $('#phone').val();
        var email = $('#email').val();
        var address = $('#address').val();
        
       
        if(username.trim()==="")
            showError("All fields are required");
        else if(fullname.trim()==="")
            showError("Please enter your name");
        else if(password.trim()==="")
            showError("Password field is required (without space)");
        else if(password2.trim()==="")
            showError("Please retype the password");
        else if(password!==password2)
            showError("Password does not match");
        else if(registertype==="student" && (branch===null || branch.trim()===""))
            showError("Branch field is required");
        else if(registertype==="student" && (semester===null || semester.trim()===""))
            showError("Semester field is required");
        else if(collegecode===null || collegecode.trim()==="")
            showError("College field is required");
        else if(phone.trim()==="")
            showError("Phone field is required");
        else if(email.trim()==="")
            showError("Email field is required");
        else if(address.trim()==="")
            showError("Address field is required");
        else {
            $.ajax({
                url: 'register',
                method: 'POST',
                data: {
                        username: username,
                        fullname: fullname,
                        password: password,
                        password2: password2,
                        branchcode: branch,
                        semester: semester,
                        collegecode: collegecode,
                        phone: phone,
                        email: email,
                        address: address,
                        registertype: registertype
                },
                success: function(response)
                {
                  if(response==="success")
                      window.location.href = "./dashboard";
                  else
                      showError(response);
                }
            });   
        }
    });
    
    $('#btnSubmitNotice').on('click', function(e){
        e.preventDefault(); 
        
        var noticetitle = $('#noticetitle').val();
        var noticebody = $('#noticebody').val();
        var attachment = document.getElementById("attachment").files[0];
        var semester = $('#semester').val();  
        var branch = $('#branch').val();
        var start_date = $('#start_date').val();
        var end_date = $('#end_date').val();
        
        //Validate input before submit and formData
        
        //end validation
        
        var formData = new FormData();
        formData.set("noticetitle",noticetitle);
        formData.set("noticebody",noticebody);
        formData.set("attachment",attachment);      
        formData.set("branchcode",branch);
        formData.set("semester",semester);
        formData.set("start_date",start_date);
        formData.set("end_date",end_date);
       
        
        $.ajax({
            url: 'notice',
            method: 'post',
            enctype: 'multipart/form-data',
            contentType: false,
            processData: false,
            data: formData,
            success: function(response) {
                if(response==="success")
                    window.location.href = "./dashboard";
                else
                    showError(response);
            },
            error: function(response) {
                showError(response);
            }
        });
    });
    
    $('.preview_data_id_notice').on('click',function() {
        var id = $(this).data('id');
        //$('.noticecontainer').load("previewnotice.jsp?id=" + id);    
        window.location.href = "./viewnotice?id=" + id;
    });
    
    function showError(x) {
        $('#error').html(x);
        $('#error').show();
    }
    
    
    var mydate = new Date();
    var mydate2 = new Date();
    mydate2.setMonth(mydate2.getMonth()+1);
    mydate2.setDate(mydate2.getDay() + 1);
    
    $('#start_date').datepicker({format: 'dd-M-yyyy',autoclose: true, startDate: mydate}).datepicker('setDate', mydate); 
    $('#end_date').datepicker({format: 'dd-M-yyyy',autoclose: true, startDate: mydate}).datepicker('setDate', mydate2);
});