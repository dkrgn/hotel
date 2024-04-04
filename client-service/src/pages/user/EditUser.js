import React, { useState } from "react";
import NavBar from "../../layout/NavBar";
import { useLocation, useNavigate } from "react-router-dom";
import "../auth/Auth.css";
import { Link } from "react-router-dom";
import axios from "axios";

export default function EditUser() {
    const navigate = useNavigate();
    const { state } = useLocation();
    const [form, setForm] = useState({
        firstName: "",
        lastName: "",
        mobileNumber: "",
        email: "",
        password: ""
    });

    const onChange = e => {
        const {name, value} = e.target;
        setForm((prev) => {
            return {...prev, [name]: value};
        })
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        let req_fname = form.firstName.trim() === "" ? state.user.firstName : form.firstName;
        let req_lname = form.lastName.trim() === "" ? state.user.lastName : form.lastName;
        let req_number = form.mobileNumber.trim() === "" ? state.user.mobileNumber : form.mobileNumber;
        let req_email = form.email.trim() === "" ? state.user.email : form.email;
        let req_password = form.password.trim() === "" ? state.user.password : form.password;
        const request = {firstName : req_fname, lastName : req_lname, mobileNumber : req_number, email : req_email, password : req_password};
            axios.put('http://localhost:8080/user-api/' + state.user.id, request)
            .then(response => {
                let data = response.data;
                console.log(data);
                if (data !== null) {
                    let email = data.email;
                    localStorage.setItem("email", email);
                    navigate("/settings");
                }
            })
            .catch(err => {
                console.log(err);
                alert("Could not update user! Error occurred!");
                window.location.reload();
            })
    }

    return(
        <div>
            <NavBar/>
            <div className="wrapper">
                <form onSubmit={handleSubmit}>
                    <div className="input-box">
                        <input type="text" onChange={onChange} name="firstName" id="first_name" placeholder={state.user.firstName}/>
                    </div>
                    <div className="input-box">
                        <input type="text" onChange={onChange} name="lastName" id="last_name" placeholder={state.user.lastName}/>
                    </div>
                    <div className="input-box">
                        <input type="text" onChange={onChange} name="mobileNumber" id="mobile_number" placeholder={state.user.mobileNumber}/>
                    </div>
                    <div className="input-box">
                        <input type="email" onChange={onChange} name="email" id="email" placeholder={state.user.email}/>
                    </div>
                    <div className="input-box">
                        <input type="password" onChange={onChange} name="password" id="password" placeholder="********"/>
                    </div>
                    <div className='input-box button'>
                        <input type="submit" id="register-button" value="Submit Edit"/>
                    </div>
                    <div className='input-box button'>
                        <Link to="/settings">Back</Link>
                    </div>
                </form>
            </div>
        </div>
    )
}