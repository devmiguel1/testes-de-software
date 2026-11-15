@echo off
cd /d "%~dp0"
echo === git add . ===
git add .
git reset -q -- "%~nx0"
echo.
echo === git commit ===
git commit -m "feat: implementa regras 2.1 a 2.5 da Fase 3 (torneio, registro de batalha, repositorio como interface)"
echo.
echo === ultimo commit ===
git log --oneline -1
echo.
git status --short
echo.
echo Esta janela fecha sozinha em 60 segundos.
timeout /t 60 >nul
(goto) 2>nul & del "%~f0"
