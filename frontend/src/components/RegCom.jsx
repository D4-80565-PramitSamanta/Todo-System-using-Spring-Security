import React, { useState } from 'react';
import { RegNew } from '../services/service';

const RegCom = () => {
    const [name, setName] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleNameChange = (event) => {
        setName(event.target.value);
    };

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        // Handle form submission here, e.g., send data to server
        const regDTO = {name, username, email, password};
        console.log(regDTO);
        RegNew(regDTO)
        .then((res)=>{console.log(res)})
        .catch((err)=>{console.log(err)});
    };

    return (
        <div>
            <h2>Register Here User !!!!!</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Name:</label>
                    <input type="text" value={name} onChange={handleNameChange} />
                </div>
                <div>
                    <label>Username:</label>
                    <input type="text" value={username} onChange={handleUsernameChange} />
                </div>
                <div>
                    <label>Email:</label>
                    <input type="email" value={email} onChange={handleEmailChange} />
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" value={password} onChange={handlePasswordChange} />
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default RegCom;
