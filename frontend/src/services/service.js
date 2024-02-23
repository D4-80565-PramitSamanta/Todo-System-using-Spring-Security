import axios from "axios";

const allurl = 'http://localhost:8080/todos/getall';
export const ListofTodos = () => axios.get(allurl);

const addurl = 'http://localhost:8080/todos/add';
export const AddTodo = (emp) => axios.post(addurl, emp);

const geturl = 'http://localhost:8080/todos/get';
export const GetTodo = (id) => axios.get(geturl + '/' + id);

