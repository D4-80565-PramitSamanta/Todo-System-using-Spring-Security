import logo from './logo.svg';
import './App.css';
import ListComponents from './components/ListComponents';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'; // Import BrowserRouter and Route separately
import TodoComonent from './components/TodoComonent';
import RegCom from './components/RegCom';
import { Footer, Header } from './components/headerfooter';
import LoginCom from './components/LoginCom';
import { ListofTodos, Login, isUseLoggedin } from './services/service';

function App() {

  function AuthenticatedRoute({ children }) {
    const isAuth = isUseLoggedin();
    if (isAuth) {
      return children;
    }
    return <Navigate to="/" />
  }


  return (
    <div className="App">
      <BrowserRouter>
        <Header></Header>
        <Routes>
          <Route path="/" element={<LoginCom />} /> 
          {/* <Route path="/all" element={<ListComponents />} /> */}
          <Route path="/all" element={<AuthenticatedRoute> <ListComponents /> </AuthenticatedRoute>} />

          <Route path="/add" element={<TodoComonent />} />
          <Route path='/edit/:id' element={<TodoComonent />} />
          <Route path='/register' element={<RegCom />} />
          <Route path='/login' element={<LoginCom/>} />
        </Routes>
        <Footer></Footer>
      </BrowserRouter>
    </div>
  );
}

export default App;
