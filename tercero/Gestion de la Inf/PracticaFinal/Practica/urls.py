"""
URL configuration for Practica project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/5.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from . import views

urlpatterns = [
    path('', views.login_view, name ='login'),
    path('pagina1/', views.pagina1, name ='pagina1'),
    path('procesar-materia-ajax/', views.procesar_materia_ajax, name='procesar_materia_ajax'),
    path('insertar-producto/', views.insertar_producto, name='insertar_producto'),
    path('borrar-producto/', views.borrar_producto, name='borrar_producto'),
    path('actualizar-producto/', views.actualizar_producto, name='actualizar_producto'),

    
]
