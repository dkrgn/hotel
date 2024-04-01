import React from "react";
import "./Register.css";

export default function Register() {
    return (
        <div className="wrapper">
            <h2>Registration</h2>
            <form>
                <div className="input-box">
                    <input type="text"  id="first_name" placeholder="First Name" required/>
                </div>
                <div className="input-box">
                    <input type="text"  id="last_name" placeholder="Last Name" required/>
                </div>
                <div className="input-box">
                    <input type="text"  id="mobile_number" placeholder="Mobile Number" required/>
                </div>
                <div className="input-box">
                    <input type="email"  id="email" placeholder="Email" required/>
                </div>
                <div className="input-box">
                    <input type="password"  id="password" placeholder="Password" required/>
                </div>
                <div className="input-box">
                    <input type="password"  id="confirm-password" placeholder="Confirm password" required/>
                </div>
                <div className='input-box button'>
                    <input type="submit" id="register-button" value="Register"/>
                </div>
            </form>
        </div>
    )
}