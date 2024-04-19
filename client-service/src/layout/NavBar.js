import React from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useNavigate } from "react-router-dom";

export default function NavBar() {
    const navigate = useNavigate();

    if (localStorage.getItem("token_id") !== null) {
        setInterval(async function() {
            logout()
        }, 1000 * 60 * 60);
    }

    function logout() {
        clearToken();
        localStorage.clear();
        navigate("/login");
    }

    const clearToken = async () => {
        axios.delete("/token/" + localStorage.getItem("token_id"))
        .then(response => console.log(response.data))
        .catch(e => console.log(e));
    }

    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <ul className="nav navbar-nav navbar-left">
                    <li><a className="navbar-brand mx-4" href="/">Hotel Home Page</a></li>
                </ul>
                <ul className="nav navbar-nav ms-auto navbar-right mx-3">
                    {
                        localStorage.getItem("token_id") !== null &&
                        <li className="dropdown"><Link to="/mybooking" className="btn btn-outline-light mx-2">MyBookings</Link></li>
                    }
                    {   localStorage.getItem("token_id") === null ? 
                        <li className="dropdown"><Link to="/login" className="btn btn-outline-light mx-4">Settings</Link></li> :
                        <li className="dropdown"><Link to="/settings" className="btn btn-outline-light mx-4">Settings</Link></li>
                    }
                    {
                        localStorage.getItem("token_id") === null && <li className="dropdown"><Link to="/login" className="btn btn-outline-light">Login</Link></li>
                    },
                    {
                        localStorage.getItem("token_id") !== null && <li className="dropdown"><button onClick={logout} className="btn btn-outline-light">Logout</button></li>
                    }
                </ul>
            </nav>
        </div>
    )
}
