import logo from './logo.svg';
import './App.css';
import ListComponents from './components/ListComponents';
import { BrowserRouter, Route, Routes } from 'react-router-dom'; // Import BrowserRouter and Route separately
import TodoComonent from './components/TodoComonent';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<ListComponents />} /> 
          <Route path="/all" element={<ListComponents />} />
          <Route path="/add" element={<TodoComonent />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
