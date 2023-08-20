package com.alianza.clientes.api.dao.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.alianza.clientes.api.dao.ClienteDao;
import com.alianza.clientes.api.entity.ClienteEntity;
import com.alianza.clientes.core.filter.ClienteFilter;
import com.alianza.clientes.core.filter.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class ClienteDaoImpl implements ClienteDao {

	private static final Logger LOGGER = Logger.getLogger(ClienteDaoImpl.class);

	@Autowired
	private ResourceLoader resourceLoader;

	private Gson gson;
	private String FILEPATH = "classpath:clientes.json";

	public ClienteDaoImpl() {
		gson = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
					@Override
					public LocalDateTime deserialize(JsonElement json, Type typeOfT,
							JsonDeserializationContext context)
							throws JsonParseException {
						return LocalDateTime.parse(json.getAsString(),
								DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
					}
				})
				.registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
					@Override
					public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
						return new JsonPrimitive(
								src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
					}
				}).create();
	}

	@Override
	public void save(ClienteEntity entity) {

		try {
			LOGGER.info("Saving new client");

			Resource resource = resourceLoader.getResource(FILEPATH);
			InputStream input = resource.getInputStream();
			Reader reader = new InputStreamReader(input, "UTF-8");
			List<ClienteEntity> clientsList = getGson().fromJson(reader, new TypeToken<List<ClienteEntity>>() {
			}.getType());
			entity.setId(clientsList.size() + 1L);
			clientsList.add(entity);
			String jsonArray = getGson().toJson(clientsList);
			reader.close();
			input.close();

			File file = resource.getFile();
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(jsonArray);
			bufferedWriter.close();
		} catch (IOException e) {
			LOGGER.error("Error while creating new client: " + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public Page<ClienteEntity> getPage(ClienteFilter filter) {
		List<ClienteEntity> clientsList = applyFilter(filter);
		Page<ClienteEntity> pagination = new Page<>();
		pagination.setContent(clientsList);
		pagination.setTotalElements(queryCount(clientsList));
		return pagination;
	}

	public List<ClienteEntity> applyFilter(ClienteFilter filter) {
		List<ClienteEntity> clients = applyWhere(applyFrom(), filter);
		return clients;
	}

	// Apply SQL Select
	public List<ClienteEntity> applyFrom() {
		List<ClienteEntity> clients = new ArrayList<>();

		try {
			Resource resource = resourceLoader.getResource(FILEPATH);
			InputStream input = resource.getInputStream();
			Reader reader = new InputStreamReader(input, "UTF-8");

			clients = getGson().fromJson(reader, new TypeToken<List<ClienteEntity>>() {
			}.getType());
			reader.close();
			input.close();
		} catch (IOException e) {
			LOGGER.error("Error while consulting clients: " + e.toString());
		}

		return clients;
	}

	// Apply SQL Where
	public List<ClienteEntity> applyWhere(List<ClienteEntity> list, ClienteFilter filter) {
		List<ClienteEntity> listAux = new ArrayList<>();
		for (ClienteEntity cliente : list) {
			boolean cumple = true;
			if ((filter.getSharedKey() != null
					&& !StringUtils.containsIgnoreCase(cliente.getSharedKey(), filter.getSharedKey()))
					|| (filter.getName() != null
							&& !StringUtils.containsIgnoreCase(cliente.getName(), filter.getName()))
					|| (filter.getEmail() != null
							&& !StringUtils.containsIgnoreCase(cliente.getEmail(), filter.getEmail()))
					|| (filter.getPhone() != null
							&& !StringUtils.containsIgnoreCase(cliente.getPhone(), filter.getPhone()))
					|| (filter.getStartDate() != null && !(cliente.getStartDate().isAfter(filter.getStartDate())))
					|| (filter.getEndDate() != null && !(cliente.getEndDate().isBefore(filter.getEndDate())))) {
				cumple = false;
			}
			if (cumple) {
				listAux.add(cliente);
			}
		}
		return listAux;
	}

	private int queryCount(List<ClienteEntity> clients) {
		return clients.size();
	}

	private Gson getGson() {
		return gson;
	}
}
