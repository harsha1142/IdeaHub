import {BrowserRouter, Routes,Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import AddIdeaPage from "./pages/AddIdeaPage";
import ViewAllIdeaPage from "./pages/ViewAllIdeaPage";
import IdeaDetailPage from "./pages/IdeaDetailPage";
import RegisterPage from "./pages/RegisterPage";
import ProtectedRoute from "./components/ProtectedRoute";



export default function App() {

  return (
    
      <BrowserRouter>
      <Routes>
      <Route path = "/" element={<LoginPage />} />
      <Route path = "/ideas" element={<ViewAllIdeaPage />} />
      <Route path = "/add" element={<AddIdeaPage />} />
      <Route path = "/ideas/:id" element={<IdeaDetailPage />} />
      <Route path ="/register" element={<RegisterPage />} />
      <Route path ="/ideas/:id" element={<ProtectedRoute><IdeaDetailPage/></ProtectedRoute>}></Route>
      </Routes>
      </BrowserRouter>
  );
    
}



