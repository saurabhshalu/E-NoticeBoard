<div class="login">
  <div class="container">
    <div class="row">
      <div class="col-md-8 m-auto">
        <h1 class="display-4 text-center">Log In</h1>
        <p class="lead text-center">Sign in to your E-Notice Board account</p>
        <form>
          <div class="form-group">
            <input type="text" class="form-control form-control-lg" placeholder="Enrollment Number" id="username" name="username" />
          </div>
          <div class="form-group">
            <input type="password" class="form-control form-control-lg" placeholder="Password" id="password" name="password" />
          </div>
                <label class="radio-inline"><input type="radio" name="logintype" value="student" checked onclick="document.getElementById('username').placeholder='Enrollment Number';">&nbsp;Student&nbsp;&nbsp;&nbsp;</label>
                <label class="radio-inline"><input type="radio" name="logintype" value="professor" onclick="document.getElementById('username').placeholder='Professor ID';">&nbsp;Professor&nbsp;&nbsp;&nbsp;</label> 
                <label class="radio-inline"><input type="radio" name="logintype" value="admin" onclick="document.getElementById('username').placeholder='admin ID';">&nbsp;Admin</label> 
                <span id="error" style="color:red; display: none;" class="float-right">Invalid user credentials</span>
          <input type="button" class="btn btn-info btn-block mt-4" name= "btnSignIn" id="btnSignIn" value="Login"/>
        </form>
      </div>
    </div>
  </div>
</div>
