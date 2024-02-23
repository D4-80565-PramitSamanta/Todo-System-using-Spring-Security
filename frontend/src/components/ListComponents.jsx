import React, { useEffect, useState } from 'react';
import { ListofTodos, DeleteTodo } from '../services/service'; // Import DeleteTodo
import { useNavigate } from 'react-router-dom';

const ListComponents = () => {
    const navigate = useNavigate();

    const [todos, setTodos] = useState([]);

    useEffect(() => {
        ListofTodos()
            .then(res => setTodos(res.data))
            .catch(err => console.log(err));
    }, []);

    const handleEdit = (todo) => {
        navigate(`/edit/${todo.id}`); // Navigate to edit page with ID
    };

    const handleDelete = (id) => {
        DeleteTodo(id)
        .then(res=>
            {
            ListofTodos()
                .then(res => setTodos(res.data))
                .catch(err => console.log(err));
            })
        .catch(err=>console.log(err));
    };

    return (
        <div style={{ textAlign: 'center', width: '50%', margin: 'auto' }}>
            <button onClick={() => navigate("/add")}>Add Todo</button>
            <h1>List Component</h1>
            <table className="table table-striped table-bordered table-hover" >
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
                                <button onClick={() => handleEdit(todo)}>Edit</button>
                                
                            </td>
                            <td>
                                <button onClick={() => handleDelete(todo.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListComponents;
