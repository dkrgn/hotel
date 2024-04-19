import React from "react";
import "./Auth.css";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";

export default function Login() {
    const [form, setForm] = useState({
        email: "",
        password: ""
    });

    const onChange = e => {
        const {name, value} = e.target;
        setForm((prev) => {
            return {...prev, [name]: value};
        })
    };
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const request = {email : form.email, password : form.password};
        axios.post('/login', request)
        .then(response => {
            let data = response.data;
            if (data.canLogin) {
                console.log(data);
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
            window.location.reload();
        })
    }
    return(
        <div className="wrapper">
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <div className="input-box">
                    <input type="email" onChange={onChange} name="email" id="email" placeholder="Email" required/>
                </div>
                <div className="input-box">
                    <input type="password" onChange={onChange} name="password" id="password" placeholder="Password" required/>
                </div>
                <div className='input-box button'>
                    <input type="submit" id="login-button" value="Login"/>
                </div>
                <div className='input-box button'>
                    <Link to="/">Back</Link>
                </div>
                <div className='input-box button'>
                    <Link to="/register">Not registered? Click here!</Link>
                </div>
            </form>
        </div>
    )
}