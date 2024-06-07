@echo off
setlocal

:: Check if Node.js is installed
where node >nul 2>nul
if %errorlevel% neq 0 (
    echo Node.js is not installed. Please install it first.
    exit /b 1
)

:: Check if Yarn is installed
where yarn >nul 2>nul
if %errorlevel% neq 0 (
    echo Yarn is not installed. Please install it first.
    exit /b 1
)

:: Navigate to the project directory
cd fs-server\fs-server-mom
if %errorlevel% neq 0 (
    echo Directory fs-server\fs-server-mom not found.
    exit /b 1
)

:: Install dependencies if node_modules does not exist
if not exist node_modules (
    echo Installing dependencies...
    yarn install
)

:: Set up environment variables from .env file if it exists
if not exist .env (
    echo .env file not found. Proceeding without environment variables.
)

:: Run the development server
echo Starting development server...
yarn dev

endlocal
