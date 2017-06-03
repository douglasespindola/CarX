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

//	@GET
//	@Path("/{cep}")
//	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
//	public CidadeDTO getCidadeByCep(@PathParam("cep") String cep) {
//		return cepService.getCidadeByCep(cep);
//	}
//
//	@POST
//	@Produces(MediaType.TEXT_PLAIN+";charset=utf-8")
//	public String insertCep(String jsonString) {
//		Gson gson = new Gson();
//		CidadeDTO cidadeDTO = gson.fromJson(jsonString, CidadeDTO.class);
//		cepService.addCidadeByCep(cidadeDTO);
//		return "Inclusão ok";
//	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public String getAllUsuarios() {
		return new Gson().toJson(usuarioService.getAllUsuarios());
	}

	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public String updateUsuario(String jsonString) {
		try {
			Gson json = new Gson();
			Usuario usuario = json.fromJson(jsonString, Usuario.class);
			return json.toJson(usuarioService.update(usuario));
		} catch (Exception e){
			Gson json = new Gson();
			MessageDto message = new MessageDto();
			message.setMessage(e.getMessage()+e.getClass());
			return json.toJson(message);
		}
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public String createUsuario(String jsonString) {
		try {
			Gson json = new Gson();
			Usuario usuario = json.fromJson(jsonString, Usuario.class);
			return json.toJson(usuarioService.create(usuario));
		} catch (Exception e){
			Gson json = new Gson();
			MessageDto message = new MessageDto();
			message.setMessage(e.getMessage()+e.getClass());
			return json.toJson(message);
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public String removeUsuario(@PathParam("id") Integer id) {
		try {
			Gson json = new Gson();
			usuarioService.remove(id);
			MessageDto message = new MessageDto();
			message.setMessage("Removido com sucesso");
			return json.toJson(message);
		} catch (Exception e){
			Gson json = new Gson();
			MessageDto message = new MessageDto();
			message.setMessage(e.getMessage()+e.getClass());
			return json.toJson(message);
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public String getUsuario(@PathParam("id") Integer id) {
		try {
			Gson json = new Gson();
			Usuario usuario = usuarioService.getUsuario(id);
			return json.toJson(usuario);
		} catch (Exception e){
			Gson json = new Gson();
			MessageDto message = new MessageDto();
			message.setMessage(e.getMessage()+e.getClass());
			return json.toJson(message);
		}
	}
}
