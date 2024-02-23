import React, { useState } from 'react';
import { AddTodo } from '../services/service';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

const TodoComponent = () => {
    const navigate = useNavigate();

    const [todo, setTodo] = useState({
        title: "",
        description: "",
        completed: false
    });

    function handleTitleChange(newTitle) {
        setTodo({ ...todo, title: newTitle });
    }

    function handleDescChange(newDesc) {
        setTodo({ ...todo, description: newDesc });
    }

    function handleSubmit(event) {
        event.preventDefault();
        AddTodo(todo)
            .then(() => navigate('/'))
            .catch(err => console.log(err));
    }

    function handleCompletedChange() {
        setTodo({ ...todo, completed: !todo.completed });
    }

    return (
        <div className="container mt-5" style={{ maxWidth: "400px" }}>
            <div className="card shadow-sm mt-3">
                <div className="card-body">
                    <h2>Add Todo</h2>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label htmlFor="title" className="form-label">Title:</label>
                            <input
                                type="text"
                                className="form-control"
                                id="title"
                                value={todo.title}
                                onChange={(e) => handleTitleChange(e.target.value)}
                                placeholder="Enter title"
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="description" className="form-label">Description:</label>
                            <textarea
                                className="form-control"
                                id="description"
                                value={todo.description}
                                onChange={(e) => handleDescChange(e.target.value)}
                                placeholder="Enter description"
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="completed">
                                Completed:
                                <input
                                    type="checkbox"
                                    className="form-check-input ms-2"
                                    id="completed"
                                    checked={todo.completed}
                                    onChange={handleCompletedChange}
                                />
                            </label>
                        </div>
                        <div>
                            <button type="submit" className="btn btn-primary">Add Todo</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default TodoComponent;
