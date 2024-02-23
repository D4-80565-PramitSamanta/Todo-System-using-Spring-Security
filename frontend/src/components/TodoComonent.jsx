import React, { useEffect, useState } from 'react';
import { AddTodo, EditTodo, GetTodo } from '../services/service';
import { useNavigate, useParams } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

const TodoComponent = () => {
    const {id} = useParams();
    useEffect(()=>
    {
        console.log(id);
        if (id) {
            GetTodo(id)
                .then(res => setTodo(res.data))
                .catch(err => console.log(err));
        }
    },[id])
    const navigate = useNavigate();

    const [todo, setTodo] = useState({
        title: "",
        description: "",
        completed: false
    });

    const [titleError, setTitleError] = useState('');
    const [descError, setDescError] = useState('');

    function handleTitleChange(newTitle) {
        setTodo({ ...todo, title: newTitle });
        setTitleError(''); // Clear error on title change
    }

    function handleDescChange(newDesc) {
        setTodo({ ...todo, description: newDesc });
        setDescError(''); // Clear error on description change
    }

    function handleSubmit(event) {
        event.preventDefault();

        const isValid = validate(); // Call validation function
        if (!isValid) return; // Exit if validation fails
        if(id){
            
            EditTodo(id,todo)
            .then(() => navigate('/'))
            .catch(err => console.log(err));
        }
        else{
            AddTodo(todo)
                .then(() => navigate('/'))
                .catch(err => console.log(err));
        }
        
    }

    function handleCompletedChange() {
        setTodo({ ...todo, completed: !todo.completed });
    }

    function validate() {
        let isValid = true;

        if (todo.title.trim() === "") {
            setTitleError("Title is required and cannot be empty spaces.");
            isValid = false;
        }

        if (todo.description.trim() === "") {
            setDescError("Description is required and cannot be empty spaces.");
            isValid = false;
        }

        return isValid;
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
                            />
                            {titleError && <span className="text-danger">{titleError}</span>}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="description" className="form-label">Description:</label>
                            <textarea
                                className="form-control"
                                id="description"
                                value={todo.description}
                                onChange={(e) => handleDescChange(e.target.value)}
                                placeholder="Enter description"
                            />
                            {descError && <span className="text-danger">{descError}</span>}
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
