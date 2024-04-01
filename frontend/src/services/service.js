import axios from "axios";

const allurl = 'http://localhost:8080/todos/getall';
export const ListofTodos = () => axios.get(allurl);

const addurl = 'http://localhost:8080/todos/add';
export const AddTodo = (emp) => axios.post(addurl, emp);

const geturl = 'http://localhost:8080/todos/get';
export const GetTodo = (id) => axios.get(geturl + '/' + id);

const editurl = 'http://localhost:8080/todos/edit';
export const EditTodo = (id,req) => axios.put(editurl + '/' + id,req);


const deleteurl = 'http://localhost:8080/todos/delete';
export const DeleteTodo = (id) => axios.delete(deleteurl + '/' + id);

const comurl = 'http://localhost:8080/todos';
export const ComTodo = (id) => axios.patch(comurl + '/' + id +  "/complete");

const incomurl = 'http://localhost:8080/todos';
export const IncomTodo = (id) => axios.patch(incomurl + '/' + id + "/in-complete");

const regURL = 'http://localhost:8080/reg/new';
export const RegNew = (regdto) => axios.post(regURL, regdto);

const loginURL = 'http://localhost:8080/reg/login';
export const Login = (logindto) => axios.post(loginURL, logindto);



axios.interceptors.request.use(
    function (config) {
        config.headers['Authorization'] = getToken();
        return config;
    },
    function (error) {
        return Promise.reject(error);
    }
);



export const storeToken = (token) => localStorage.setItem("token",token);

export const getToken = () => localStorage.getItem("token");

export const saveLoggedinUser = (username, role)=> { 
    sessionStorage.setItem("authinticatedUser",username);
    sessionStorage.setItem("role",role);
}


export const isUseLoggedin = () => {
    const username = sessionStorage.getItem("authinticatedUser");
    if(username==null)
    {
        return false;
    }else{
        return true;
    }
}

export const getLoggedinuser = ()=>
{
    const username = sessionStorage.getItem("authinticatedUser");
    return username;
}


export const Logout = ()=>{
    localStorage.clear();
    sessionStorage.clear();
}

export const isAdmin = ()=>{
    let role = sessionStorage.getItem("role");
    if (role != null && role ==='ROLE_ADMIN')
    {
        return true;
    }
    else
    {
        return false;
    }
}