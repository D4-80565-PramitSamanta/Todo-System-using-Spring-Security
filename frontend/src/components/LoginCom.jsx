import React, { useState } from 'react';
import { Login, saveLoggedinUser, storeToken } from '../services/service';
import { useNavigate } from 'react-router-dom';

const LoginCom = () => {
    const nav = useNavigate();
    const [usernameoremail, setUsernameoremail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = (event) => {
        event.preventDefault();
        console.log("Username/Email:", usernameoremail);
        console.log("Password:", password);

        const logindto = { usernameoremail, password };
        Login(logindto)
            .then((res) => {
                console.log(res);
                const token = 'Bearer ' + res.data.accessToken;
                const role = res.data.role;
                saveLoggedinUser(usernameoremail, role);
                storeToken(token);
                nav("/all");
                window.location.reload(); // Consider alternative for potential state issues (see note)
            })
            .catch((err) => console.log(err));
    };

    return (
        <div className="login-container"> {/* Apply container class */}
            <h2>Login</h2>
            <form onSubmit={handleLogin} className="login-form"> {/* Apply form class */}
                <div className="form-group"> {/* Group form elements */}
                    <label htmlFor="usernameoremail">Username or Email:</label>
                    <input
                        type="text"
                        id="usernameoremail"
                        value={usernameoremail}
                        onChange={(e) => setUsernameoremail(e.target.value)}
                        className="form-control" // Apply input styling
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="form-control" // Apply input styling
                    />
                </div>
                <button type="submit" className="login-btn">
                    Login
                </button>
            </form>
        </div>
    );
};

export default LoginCom;
