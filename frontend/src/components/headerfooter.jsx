import React from 'react';
import { NavLink } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'; // Include Bootstrap CSS

const Header = () => {
    return (
        <header className="container-fluid bg-light d-flex justify-content-between align-items-center py-3">
            <div>
                <h1>Cool Todo App</h1>
                <p>Organize your tasks efficiently</p>
            </div>
            <nav className="nav">
                <ul className="nav-pills">
                    <li className="nav-item">
                        <NavLink to="/all" className="nav-link">Todos</NavLink>
                    </li>
                    <li className="nav-item">
                        <NavLink to="/register" className="nav-link">Register</NavLink>
                    </li>
                </ul>
            </nav>
        </header>
    );
};

const Footer = () => {
    return (
        <footer className="container-fluid bg-dark text-light text-center py-3">
            <div>
                <p>© {new Date().getFullYear()} Cool Todo App. All rights reserved.</p>
            </div>
        </footer>
    );
};

export { Header, Footer };
