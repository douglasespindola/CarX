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

import com.google.gson.Gson;
import dto.MessageDto;
import dto.TokenDto;
import entity.User;
import service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getAll() {
        try {
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(new Gson().toJson(userService.getAll())).build();
        } catch (Exception e) {
            Gson json = new Gson();
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }


    @PUT
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response update(String jsonString) {
        try {
            Gson json = new Gson();
            User user = json.fromJson(jsonString, User.class);
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(userService.update(user)).build();
        } catch (Exception e) {
            Gson json = new Gson();
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response create(String jsonString) {
        try {
            Gson json = new Gson();
            User user = json.fromJson(jsonString, User.class);
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(userService.create(user))).build();
        } catch (Exception e) {
            Gson json = new Gson();
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }

    @DELETE
    @Path("/admin/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response remove(@PathParam("id") Integer id) {
        try {
            Gson json = new Gson();
            userService.remove(id);
            MessageDto message = new MessageDto();
            message.setMessage("Removido com sucesso");
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(message)).build();
        } catch (Exception e) {
            Gson json = new Gson();
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }

    @GET
    @Path("/admin/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get(@PathParam("id") Integer id) {
        try {
            Gson json = new Gson();
            User user = userService.get(id);
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(user)).build();
        } catch (Exception e) {
            Gson json = new Gson();
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }
    @POST
    @Path("/admin/signOut")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response signOut(String jsonString) {
        //TODO implementar
        MessageDto message = new MessageDto();
        message.setMessage("flw");
        return Response.status(200).type(MediaType.APPLICATION_JSON).entity(message).build();
    }

    @POST
    @Path("/getToken")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getToken(String jsonString) {
        try {
            Gson json = new Gson();
            User user = json.fromJson(jsonString, User.class);
            TokenDto token = userService.getToken(user);
            if(token.getToken()==null) {
                return Response.status(400).type(MediaType.APPLICATION_JSON).entity(json.toJson(token)).build();
            }
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(token)).build();
        } catch (Exception e) {
            Gson json = new Gson();
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }

}
