import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './app'
import "./app/styles/fonts.css"
import '@radix-ui/themes/styles.css';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)
