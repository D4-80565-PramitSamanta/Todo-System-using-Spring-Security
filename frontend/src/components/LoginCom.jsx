import React, { useState } from 'react';
import { Login, saveLoggedinUser, storeToken } from '../services/service';
import { useNavigate } from 'react-router-dom';

const LoginCom = () => {
    const nav = useNavigate();
    const [usernameoremail, setusernameoremail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = (event) => {
        event.preventDefault();
        console.log("Username/Email:", usernameoremail);
        console.log("Password:", password);
        const logindto = { usernameoremail,password};
        Login(logindto)
        .then(res=>{
            console.log(res);
            const token = 'Basic ' + window.btoa(usernameoremail + ":" + password);
            saveLoggedinUser(usernameoremail);
            storeToken(token);
            nav("/all");
            window.location.reload();
    })
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
