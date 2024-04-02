import React from 'react';
import { Link } from 'react-router-dom';

export default function NavBar() {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <ul className="nav navbar-nav navbar-left">
                    <li><a className="navbar-brand mx-4" href="/">Hotel Home Page</a></li>
                </ul>
                <ul className="nav navbar-nav ms-auto navbar-right mx-3">
                    <li className="dropdown"><Link to="/account" className="btn btn-outline-light mx-4">Account</Link></li>
                    <li className="dropdown"><Link to="/login" className="btn btn-outline-light">Login</Link></li>
                </ul>
            </nav>
        </div>
    )
}
