import React from 'react';

const Header = () => {
    return (
        <header className="bg-dark text-white py-4">
            <div className="container text-center">
                <h1 className="display-4">Cool Todo App</h1>
                <p className="lead">Organize your tasks efficiently</p>
            </div>
        </header>
    );
};

const Footer = () => {
    return (
        <footer className="bg-dark text-white py-3">
            <div className="container text-center">
                <p>&copy; {new Date().getFullYear()} Cool Todo App. All rights reserved.</p>
            </div>
        </footer>
    );
};

export { Header, Footer };
