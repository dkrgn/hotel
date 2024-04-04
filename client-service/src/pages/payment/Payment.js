import React, { useState } from "react";
import NavBar from "../../layout/NavBar";
import "../../pages/auth/Auth.css";
import { useLocation } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { format } from "date-fns";

export default function Payment() {
    const { state } = useLocation();
    const navigate = useNavigate();
    const [form, setForm] = useState({
        card: "",
        cvv: ""
    });

    const [selected, setSelected] = useState(null);

    const onOptionChange = (i) => {
        setSelected((prev) => (i === prev ? null : i));
    };

    const options = [
        "DEBIT", "CREDIT"
    ]

    const onChange = e => {
        const {name, value} = e.target;
        setForm((prev) => {
            return {...prev, [name]: value};
        })
    };
    
    function makeBooking(e) {
        e.preventDefault();
        alert("Payment with credentials: number{" + form.card + "}, cvv{" + 
        form.cvv + "}, card type{" + options[selected] + "} has been processed!");
        const id = localStorage.getItem("user_id");
        const email = localStorage.getItem("email");
        let currentDate = format(new Date(), 'yyyy-MM-dd\'T\'HH:mm:ss.SSS');
        const payment = {
            userId : id,
            roomId : state.room.id,
            type : options[selected],
            issuedAt : currentDate
        };
        const booking = {
            userId : id,
            roomId : state.room.id,
            paymentId : null,
            start : state.startDate,
            end : state.endDate
        }
        const request = {paymentRequest : payment, bookingRequest : booking, email: email}
        post(request);
        console.log(request)
        
    }

    const post = async (request) => {
        axios.post('http://localhost:8080/booking-api', request)
        .then(response => {
            if (response.paymentResponse === null || response.bookinResponse === null) {
                alert("Error occurred! Could not process payment and booking.")
                window.location.reload();
            } else {
                console.log(response);
                alert("Booking has been successfully saved!");
                navigate("/");
            }
        })
        .catch(err => {
            console.log(err);
        })
    }

    return (
        <div>
            <NavBar/>
            <div className="wrapper">
                <h2>Payment Information</h2>
                <form onSubmit={makeBooking}>
                    <div className="input-box">
                        <input type="text" onChange={onChange} name="card" id="email" placeholder="Card Number" required/>
                    </div>
                    <label htmlFor="cars">Choose Card Type:</label>
                    <div>
                        {options.map((o, i) => (
                            <label key={i}>
                            {o}
                            <input
                                className="ml-2"
                                type="checkbox"
                                checked={i === selected}
                                onChange={() => onOptionChange(i)}
                            />
                            </label>
                        ))}
                    </div>
                    <div className="input-box">
                        <input type="text" onChange={onChange} name="cvv" id="password" placeholder="CVV" required/>
                    </div>
                    <div className='input-box button'>
                        <input type="submit" id="payment-button" value="Pay"/>
                    </div>
                    <div className='input-box button'>
                        <button onClick={() => navigate(-1)}>Back</button>
                    </div>
                </form>
            </div>
        </div>
    )
};