import React, { useEffect, useState } from "react";
import NavBar from "../../layout/NavBar";
import axios from "axios";
import "../auth/Auth.css";
import { Link } from "react-router-dom";

export default function MyBooking() {
    const [editBooking, setEditBookings] = useState(null);
    const loadEditBookings = async () => {
        await axios.get("http://localhost:8080/booking-api/" + localStorage.getItem("user_id"))
        .then(response => {
            console.log(response.data);
            setEditBookings(response.data);
        })
        .catch(e => {
            console.log(e);
        });
    }
    useEffect(() => {
        loadEditBookings();
    }, [])

    const deleteBooking = (b_id, r_id) => {
        const confirmed = window.confirm("Are you sure you want to delete your booking?");
        if (confirmed) {
            bookingRemoval(b_id, r_id);
            window.location.reload();
        }
    }

    const bookingRemoval = async(b_id, r_id) => {
        const response = await axios.delete("http://localhost:8080/booking-api/" + b_id + "/" + r_id);
        if (response.data !== null) {
            alert("Booking was successfully deleted!");
        }
    }

    return (
        <div>
            <NavBar/>
            {
                editBooking === null &&
                <div className="container">
                    <h1>No Bookings Yet</h1>
                </div>
            }
            {
                editBooking !== null &&
                <div className="container">
                <h1>My Bookings</h1>
                <div className="py-4">
                    <table className="table border shadow">
                        <thead>
                            <tr>
                                <th scope="col">Reference #</th>
                                <th scope="col">Room Type</th>
                                <th scope="col">Price</th>
                                <th scope="col">Description</th>
                                <th scope="col">Capacity</th>
                                <th scope="col">Start Date</th>
                                <th scope="col">End Date</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        {
                            editBooking.map(e => (
                                <tr key={e.bookingResponse.id}>
                                    <td>{e.bookingResponse.id}</td>
                                    <td>{e.roomResponse.type}</td>
                                    <td>{e.roomResponse.price}</td>
                                    <td>{e.roomResponse.description}</td>
                                    <td>{e.roomResponse.capacity}</td>
                                    <td>{e.bookingResponse.start}</td>
                                    <td>{e.bookingResponse.end}</td>
                                    <td>
                                        <Link to="/editbooking" state={{e : e}} className="input-box button">Edit</Link>
                                        <button onClick={() => deleteBooking(e.bookingResponse.id, e.roomResponse.id)} className="btn btn-danger mx-2">Delete</button>
                                    </td>  
                                </tr>
                                
                            ))
                        }
                        </tbody>
                    </table>
                </div>
            </div>
            }
        </div>
    )
}