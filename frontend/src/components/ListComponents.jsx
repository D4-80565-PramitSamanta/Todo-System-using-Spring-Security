import React, { useEffect, useState } from 'react';
import { ListofTodos } from '../services/service';
import { useNavigate } from 'react-router-dom';

const ListComponents = () => {
    const navigate = useNavigate();
    function handleAdd()
    {
        navigate("/add");
    }
    const dummy = [
        {
            "id": 1,
            "title": "Title 1",
            "description": "Description 1",
            "completed": false
        }
    ];
    function populate()
    {
        ListofTodos()
        .then(res=>setTodos(res.data))
        .catch(err=>console.log(err));
    }
    const [todos, setTodos] = useState(dummy);
    useEffect(()=>{
        populate();
    },[])
    return (
     
        <div style={{ textAlign: 'center', width: '50%', margin: 'auto' }}>
            <button onClick={handleAdd}> Add Todo</button>
            <h1>List Component</h1>
            <table className="table table-striped table-bordered table-hover" style={{ backgroundColor: '#ff6f00', color: 'white' }}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Completed</th>
                    </tr>
                </thead>
                <tbody>
                    {todos.map(todo => (
                        <tr key={todo.id} style={{ backgroundColor: '#ffa796' }}>
                            <td>{todo.id}</td>
                            <td>{todo.title}</td>
                            <td>{todo.description}</td>
                            <td>{todo.completed ? 'Yes' : 'No'}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListComponents;
