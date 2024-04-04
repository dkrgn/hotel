import React, { useState } from "react";
import "./Auth.css";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

export default function Register() {
    const [form, setForm] = useState({
        firstName: "",
        lastName: "",
        mobileNumber: "",
        email: "",
        password: "",
        confirmPassword: ""
    });

    const navigate = useNavigate();

    const onChange = e => {
        const {name, value} = e.target;
        setForm((prev) => {
            return {...prev, [name]: value};
        })
    };
    
    const handleSubmit = (e) => {
        e.preventDefault();
        if (form.password === form.confirmPassword) {
            const request = {firstName : form.firstName, lastName : form.lastName, mobileNumber : form.mobileNumber, email : form.email, password : form.password};
            axios.post('http://localhost:8080/register', request)
            .then(response => {
                let data = response.data;
                console.log(data);
                if (data.saved) {
                    let email = data.email;
                    let token_id = data.tokenResponse.id;
                    let user_id = data.tokenResponse.userId;
                    localStorage.setItem("token_id", token_id);
                    localStorage.setItem("user_id", user_id);
                    localStorage.setItem("email", email);
                    navigate("/");
                }
            })
            .catch(err => {
                console.log(err);
                alert("Email either taken or registration error occurred!");
                window.location.reload();
            })
        }
    }

    return (
        <div className="wrapper">
            <h2>Registration</h2>
            <form onSubmit={handleSubmit}>
                <div className="input-box">
                    <input type="text" onChange={onChange} name="firstName" id="first_name" placeholder="First Name" required/>
                </div>
                <div className="input-box">
                    <input type="text" onChange={onChange} name="lastName" id="last_name" placeholder="Last Name" required/>
                </div>
                <div className="input-box">
                    <input type="text" onChange={onChange} name="mobileNumber" id="mobile_number" placeholder="Mobile Number" required/>
                </div>
                <div className="input-box">
                    <input type="email" onChange={onChange} name="email" id="email" placeholder="Email" required/>
                </div>
                <div className="input-box">
                    <input type="password" onChange={onChange} name="password" id="password" placeholder="Password" required/>
                </div>
                <div className="input-box">
                    <input type="password" onChange={onChange} name="confirmPassword" id="confirm_password" placeholder="Confirm password" required/>
                </div>
                <div className='input-box button'>
                    <input type="submit" id="register-button" value="Register"/>
                </div>
                <div className='input-box button'>
                    <Link to="/">Back</Link>
                </div>
                <div className='input-box button'>
                    <Link to="/login">Already registered? Click here!</Link>
                </div>
            </form>
        </div>
    )
}