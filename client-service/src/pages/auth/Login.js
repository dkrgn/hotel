import React from "react";
import "./Login.css";

export default function Login() {
    return(
        <div className="wrapper">
            <h2>Login</h2>
            <form>
                <div className="input-box">
                    <input type="email" id="email" placeholder="Email" required/>
                </div>
                <div className="input-box">
                    <input type="password" id="password" placeholder="Password" required/>
                </div>
                <div className='input-box button'>
                    <input type="submit" id="login-button" value="Login"/>
                </div>
            </form>
        </div>
    )
}