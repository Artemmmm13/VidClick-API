import './index.css'
import {RouterProvider} from "react-router-dom";
import {router} from "./providers/react-router";
import {Theme} from "@radix-ui/themes";

function App() {
  return (
      <Theme>
        <RouterProvider router={router}/>
      </Theme>
  )
}

export default App
