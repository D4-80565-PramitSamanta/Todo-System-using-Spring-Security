import React, { useEffect, useState } from 'react';
import { ListofTodos, UpdateTodo } from '../services/service'; // Import UpdateTodo
import { useNavigate } from 'react-router-dom';

const ListComponents = () => {
    const navigate = useNavigate();

    const [todos, setTodos] = useState([]);

    useEffect(() => {
        ListofTodos()
            .then(res => setTodos(res.data))
            .catch(err => console.log(err));
    }, []);

    const handleEdit = (i) => {
        console.log(i);
       navigate(`/edit/${i}`); // Navigate to edit page with ID
    };

    return (
        <div style={{ textAlign: 'center', width: '50%', margin: 'auto' }}>
            <button onClick={() => navigate("/add")}>Add Todo</button>
            <h1>List Component</h1>
            <table className="table table-striped table-bordered table-hover" style={{ backgroundColor: '#ff6f00', color: 'white' }}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Completed</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {todos.map((todo) => (
                        <tr key={todo.id} style={{ backgroundColor: '#ffa796' }}>
                            <td>{todo.id}</td>
                            <td>{todo.title}</td>
                            <td>{todo.description}</td>
                            <td>{todo.completed ? 'Yes' : 'No'}</td>
                            <td>
                                <button onClick={() => handleEdit(todo.id)}>Edit</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListComponents;
