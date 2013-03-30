function load() {
    console.log('Google API loading...');
    /* gapi.client.setApiKey('311668897898.apps.googleusercontent.com');*/
    gapi.client.load('oauth2', 'v2', authorize);
    $('#authorize').click(authorize);
    console.log('hey2');
}
    
function authorize(event) {
    // Step 3: get authorization to use private data
    gapi.auth.authorize({
        client_id: '311668897898.apps.googleusercontent.com', 
        scope: [
        'https://www.googleapis.com/auth/userinfo.email',
        'https://www.googleapis.com/auth/userinfo.profile'
        ], 
        immediate: false
    }, handle_token);
    return false;
}

function handle_token(auth_result) {
    console.log(auth_result)
    if (auth_result && !auth_result.error) {
        gapi.auth.setToken(auth_result);
        var request = gapi.client.oauth2.userinfo.get();
        request.execute(handle_email);
        //
        $('.piubella-notconnected').hide();
        $('.piubella-connected').show();
    } else {
        $('.piubella-notconnected').show();
        $('.piubella-connected').hide();
    }
}

function handle_email(email_result) {
    console.log(email_result);
    if (email_result && email_result.email) {
        $('#email').html(email_result.email);
        $('#log_in').hide();
        $('#logged_in').show();
    } else {
        $('#logged_in').hide();
        $('#log_in').show();
    }
}

function make_call() {
    
}