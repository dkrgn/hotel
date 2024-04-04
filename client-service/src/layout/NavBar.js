import React from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function NavBar() {
    const navigate = useNavigate();
    function logout() {
        clearToken();
        localStorage.clear();
        navigate("/");
    }

    const clearToken = async () => {
        const response = await axios.delete("http://localhost:8080/token/" + localStorage.getItem("token_id"));
        console.log(response.data);
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
