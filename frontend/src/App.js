import logo from './logo.svg';
import './App.css';
import ListComponents from './components/ListComponents';
import { BrowserRouter, Route, Routes } from 'react-router-dom'; // Import BrowserRouter and Route separately
import TodoComonent from './components/TodoComonent';
import RegCom from './components/RegCom';
import { Footer, Header } from './components/headerfooter';
import LoginCom from './components/LoginCom';
import { Login } from './services/service';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header></Header>
        <Routes>
          <Route path="/" element={<LoginCom />} /> 
          <Route path="/all" element={<ListComponents />} />
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
