# Deployment Guide

## Backend → Render

1. Push your `backend/` folder to a GitHub repo
2. Go to [render.com](https://render.com) → New → Web Service
3. Connect your GitHub repo
4. Set these values:
   - **Environment**: Java
   - **Build Command**: `mvn clean package -DskipTests`
   - **Start Command**: `java -jar target/cafena-backend-1.0.0.jar`
5. Add this **Environment Variable** in Render dashboard:
   - `ALLOWED_ORIGINS` = `https://your-app.vercel.app`
     *(replace with your actual Vercel frontend URL)*
6. Deploy — Render will give you a URL like `https://cafena-backend.onrender.com`

## Frontend → Vercel

1. In your Vercel project dashboard → Settings → Environment Variables
2. Add:
   - `VITE_API_URL` = `https://cafena-backend.onrender.com`
     *(your Render backend URL from step 6 above)*
3. Redeploy the frontend on Vercel

## Local Development

No changes needed. The Vite dev proxy (`vite.config.js`) forwards `/api/*` calls
to `localhost:8080` automatically. Just run both servers:

```bash
# Terminal 1 - backend
cd backend && mvn spring-boot:run

# Terminal 2 - frontend
cd frontend && npm run dev
```
