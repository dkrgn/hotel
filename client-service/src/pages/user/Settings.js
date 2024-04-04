import React from "react";
import NavBar from "../../layout/NavBar";
import { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "../auth/Auth.css";
import { useNavigate } from "react-router-dom";

export default function Account() {
    const navigate = useNavigate();
    const [user, setUser] = useState([]);
    useEffect(() => {
        loadUser();
    }, []);

    const loadUser = async () => {
        const response = await axios.get("http://localhost:8080/user-api/" + localStorage.getItem("user_id"));
        setUser(response.data);
    }

    const clearToken = async () => {
        const response = await axios.delete("http://localhost:8080/token/" + localStorage.getItem("token_id"));
        console.log(response.data);
    }

    const deletion = async() => {
        const response = await axios.delete("http://localhost:8080/user-api/" + localStorage.getItem("user_id"));
            if (response.data !== null) {
                console.log("user was deleted!");
                clearToken();
                localStorage.clear();
                navigate("/");
            } else {
                alert("Error occurred when deleting account!");
            }
    }

    const bookingRemoval = async () => {
        const response = await axios.delete("http://localhost:8080/booking-api/" + localStorage.getItem("user_id"));
        if (response === null) {
            console.log("Could not delete user's bookings")
        } else {
            console.log("Bookings were deleted!")
        }
    }

    const deleteUser = () => {
        const confirmed = window.confirm("Are you sure you want to delete your account?");
        if (confirmed) {
            bookingRemoval();
            deletion();
        }
    }

    return(
        <div>
            <NavBar/>
            <div className="container">
                <h1>Settings</h1>
                <div className="py-4">
                    <table className="table border shadow">
                        <thead>
                        <tr>
                            <th scope="col">First Name</th>
                            <th scope="col">Last Name</th>
                            <th scope="col">Mobile Number</th>
                            <th scope="col">Email</th>
                            <th scope="col">Password</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>{user.firstName}</td>
                                <td>{user.lastName}</td>
                                <td>{user.mobileNumber}</td>
                                <td>{user.email}</td>
                                <td>********</td>
                                <td>
                                    <Link to="/edituser" state={{user : user}} className="input-box button">Edit</Link>
                                    <button onClick={deleteUser} className="btn btn-danger mx-2">Delete</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}