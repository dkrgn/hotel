import React from "react";
import NavBar from "../../layout/NavBar";
import axios from "axios";

export default function MyBooking() {
    return (
        <div>
            <NavBar/>
            <h1>My Bookings</h1>
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