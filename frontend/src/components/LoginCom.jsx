import React, { useState } from 'react';
import { Login, storeToken } from '../services/service';

const LoginCom = () => {
    const [usernameoremail, setusernameoremail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = (event) => {
        event.preventDefault();
        console.log("Username/Email:", usernameoremail);
        console.log("Password:", password);
        const token = 'Basic ' + window.btoa(usernameoremail + ":" + password);
        storeToken(token);
        const logindto = { usernameoremail,password};
        Login(logindto)
        .then(res=>{console.log(res)})
        .catch(err=>{console.log(err)});
    };

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <div>
                    <label>Username or Email:</label>
                    <input
                        type="text"
                        value={usernameoremail}
                        onChange={(e) => setusernameoremail(e.target.value)}
                    />
                </div>
                <div>
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default LoginCom;
