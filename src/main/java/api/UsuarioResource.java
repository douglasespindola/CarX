/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package api;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import dto.MessageDto;
import entity.Usuario;
import service.UsuarioService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Path("/usuario")
public class UsuarioResource {
	
	@Inject
	private UsuarioService usuarioService;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public String getAllUsuarios() {
		return new Gson().toJson(usuarioService.getAllUsuarios());
	}

	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response updateUsuario(String jsonString) {
		try {
			Gson json = new Gson();
			Usuario usuario = json.fromJson(jsonString, Usuario.class);
			return Response.status(200).type(MediaType.APPLICATION_JSON).entity(usuarioService.update(usuario)).build();
		} catch (Exception e){
			Gson json = new Gson();
			MessageDto message = new MessageDto();
			message.setMessage(e.getMessage()+e.getClass());
			return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
		}
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response createUsuario(String jsonString) {
		try {
			Gson json = new Gson();
			Usuario usuario = json.fromJson(jsonString, Usuario.class);
			return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(usuarioService.create(usuario))).build();
		} catch (Exception e){
			Gson json = new Gson();
			MessageDto message = new MessageDto();
			message.setMessage(e.getMessage()+e.getClass());
			return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response removeUsuario(@PathParam("id") Integer id) {
		try {
			Gson json = new Gson();
			usuarioService.remove(id);
			MessageDto message = new MessageDto();
			message.setMessage("Removido com sucesso");
			return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(message)).build();
		} catch (Exception e){
			Gson json = new Gson();
			MessageDto message = new MessageDto();
			message.setMessage(e.getMessage()+e.getClass());
			return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getUsuario(@PathParam("id") Integer id) {
		try {
			Gson json = new Gson();
			Usuario usuario = usuarioService.getUsuario(id);
			return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(usuario)).build();
		} catch (Exception e){
			Gson json = new Gson();
			MessageDto message = new MessageDto();
			message.setMessage(e.getMessage()+e.getClass());
			return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
		}
	}
}
