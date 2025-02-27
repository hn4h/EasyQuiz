/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
function showDetails(type) {
    let title = "";
    let content = "";
    switch (type) {
        case 'signup':
            title = "Signing up for a free account";
            content = `
                <p>You can create a free account using your email address or Google account.</p>
            <br/>
                <p><strong>To sign up with email:</strong></p>
                <ol>
                    <li>1. Go to the <a href="#" target="_blank">EasyQuiz homepage</a> and select <strong>Sign up</strong>.</li>
                    <li>2. Enter your email address.</li>
                    <li>3. Enter your username.</li>
                    <li>4. Enter a secure password.</li>
                    <li>5. Select <strong>Sign up</strong>.</li>
                    <li>6. Check your email and confirm your account.</li>
                </ol>
            <br/>
                <p><strong>To sign up with Google:</strong></p>
                <p>If you have a Google account, you can quickly sign up using that account.</p>
                <ol>
                    <li>1. Go to the <a href="#" target="_blank">EasyQuiz homepage</a> and select <strong>Sign up</strong>.</li>
                    <li>2. Select Continue with Google.</li>
                    <li>3. If you aren't already logged in to Google, enter your Google username and password.</li>
                    <li>4. Select <strong>Sign up</strong>.</li>
                    <li>5. Check your email and confirm your account.</li>
            </ol>
            `;
            break;
        case 'login':
            title = "Trouble logging in";
            content = `<p>We're sorry to hear that you're having trouble logging in! Below, find the section that best describes your situation, and follow the steps to log in.</p>     
        <br/>

<p><strong>EasyQuiz says I'm using the wrong Google account</strong></p>
<ol>
    <li>Go to <a href="#">Google</a>.</li>
    <li>Select the <strong>Profile picture</strong>.</li>
    <li>If you don't see your information there, you may be in someone else's Google account—most likely a friend or classmate's, if you're using a shared device.</li>
    <li>Select <strong>Sign out</strong>.</li>
    <li>Go to <a href="#">EasyQuiz</a>.</li>
    <li>Select <strong>Log in with Google</strong>.</li>
    <li>Sign in with your Google account.</li>
</ol>

<br/>

<p><strong>I'm trying to log in with Google and am hitting an error message</strong></p>
<p>If you’re running into any of the following error messages, it might be because your organization has restricted access to Quizlet through Google Workspace:</p>
<ul>
    <li>Access blocked: Your institution’s admin needs to review this app</li>
    <li>Error 400: access_not_configured</li>
    <li>Error 400: admin_policy_enforced</li>
</ul>
<br/>

<p><strong>EasyQuiz says I've tried to log in too many times</strong></p>
<p>You'll see this message after too many unsuccessful login tries. The easiest way to get logged back in is to reset your password from the <a href="#">Forgot password page</a>. If you need more help, follow these steps to <a href="#">reset your password</a>.</p>

<br/>

<p><strong>I deleted my account and I want to open a new one with the same username</strong></p>
<p>Once you've created an account, that username can't be reused, even after the original account has been deleted.</p>`;
            break;
        case 'username':
            title = "Changing your username";
            content = `
            <p><strong>To change your username:</strong></p>
<ol>
  <li>1. Log in to your account.</li>
  <li>2. Select your profile image.</li>
  <li>3. Select <strong>Settings</strong>.</li>
  <li>4. Scroll to <strong>Change your username</strong>.</li>
  <li>5. Enter your password.</li>
  <li>6. Select <strong>Continue</strong>.</li>
  <li>7. Enter a new username.</li>
  <li>8. Select <strong>Permanently change your username</strong> to save.</li>
</ol>
`;
            break;
        case 'password':
            title = "Changing your password";
            content = `
 <p>You can change your password from your <strong>Settings</strong>.</p>
<br/>
<p><strong>To change your password:</strong></p>
<ol>
  <li>1. Log in to your account.</li>
  <li>2. Select <strong>Settings</strong>.</li>
  <li>3. Scroll to <strong>Change your password</strong>.</li>
  <li>4. Enter your current password.</li>
  <li>5. Enter and confirm your new password.</li>
  <li>6. Select <strong>Save</strong> to save your new password.</li>
</ol>
<br/>
<p>If you’ve forgotten your password, <a href="#">reset your password</a>.</p>           
`;
            break;
        case 'forgot':
            title = "Forgotten password";
            content = `
<p><strong>To reset your password:</strong></p>
<ol>
  <li>1. Go to the <a href="#">Forgot password</a> page.</li>
  <li>2. Enter your username or email.</li>
  <li>3. Select <strong>Reset password</strong>.</li>
  <li>4. You'll get a password reset message at the email associated with your EasyQuiz account.</li>
</ol>            
`;
            break;
        case 'logout':
            title = "Logging out";
            content = `
<p>When you log in to your EasyQuiz account on the website, you'll stay logged in unless you specifically log out. Even after you've closed your browser or restarted your computer, you'll still be logged in so you can quickly get back to studying.</p>
<br/>
<p>If you're using a public or shared computer, make sure you log out when you're done.</p>
<br/>
<p><strong>To log out:</strong></p>
<ol>
  <li>1. Select your profile image.</li>
  <li>2. Select <strong>Log Out</strong>.</li>
</ol>            
`;
            break;
        case 'delete':
            title = "Deleting your account";
            content = `
<p><strong>To delete your account:</strong></p>
<ol>
  <li>1. Log in to your account on the EasyQuiz website.</li>
  <li>2. Go to <strong>Settings</strong> under your profile picture.</li>
  <li>3. Select <strong>Delete Account</strong> at the bottom of the page.</li>
  <li>4. Enter your password or authenticate with Google or Facebook.</li>
  <li>5. Select <strong>Continue</strong> to delete your account.</li>
</ol>
<br/>
<p>You remain in control of the information you provide to EasyQuiz. You may review, edit or delete any information you submit to us at any time by signing into your EasyQuiz account; however, deletion of this information may restrict your ability to effectively use the Service. You may also delete your account at any time. If you delete your account, all the quizzes and folders that you've created will also be also deleted in accordance with our data retention practices.</p>
<br/>
<p>Please know that once you delete your account, it can't be recovered.</p>           
`;
            break;
    }
    document.getElementById('detailTitle').textContent = title;
    document.getElementById('detailContent').innerHTML = content;
    document.getElementById('detailView').classList.remove('hidden');
}
function hideDetails() {
    document.getElementById('detailView').classList.add('hidden');
}


