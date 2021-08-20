package com.manhpd.webclient_utils.utils;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.manhpd.webclient_utils.model.http_error_code.AcceptHttp;
import com.manhpd.webclient_utils.model.http_error_code.BadRequestHttp;
import com.manhpd.webclient_utils.model.http_error_code.ConflictHttp;
import com.manhpd.webclient_utils.model.http_error_code.CreatedHttp;
import com.manhpd.webclient_utils.model.http_error_code.IHttpResponse;
import com.manhpd.webclient_utils.model.http_error_code.NoContentHttp;
import com.manhpd.webclient_utils.model.http_error_code.NotFoundHttp;
import com.manhpd.webclient_utils.model.http_error_code.OkHttp;
import com.manhpd.webclient_utils.model.http_error_code.SeeOtherHttp;
import com.manhpd.webclient_utils.model.http_error_code.UnauthorizedHttp;
import com.manhpd.webclient_utils.model.response.Response;

import reactor.core.publisher.Mono;

@Component
public class RequestUtils {

	@Autowired
	private WebClient webClient;

	private static Map<HttpStatus, IHttpResponse> responseHttp = new HashMap<>();

	static {
		responseHttp.put(HttpStatus.OK, new OkHttp());
		responseHttp.put(HttpStatus.CREATED, new CreatedHttp());
		responseHttp.put(HttpStatus.ACCEPTED, new AcceptHttp());
		responseHttp.put(HttpStatus.NO_CONTENT, new NoContentHttp());
		responseHttp.put(HttpStatus.SEE_OTHER, new SeeOtherHttp());
		responseHttp.put(HttpStatus.UNAUTHORIZED, new UnauthorizedHttp());
		responseHttp.put(HttpStatus.BAD_REQUEST, new BadRequestHttp());
		responseHttp.put(HttpStatus.NOT_FOUND, new NotFoundHttp());
		responseHttp.put(HttpStatus.CONFLICT, new ConflictHttp());
	};

	public Mono<Response> getRequest(String path, String valueInput, ResultType type) throws IOException {
		Response Response = ResultBOFactory.createResultBO(type);
		Mono<ClientResponse> res = get(valueInput, path);

		return toResponseResult(Response, res);
	}

	public Mono<Response> postRequest(String path, String valueInput, ResultType type) throws IOException {
		Response Response = ResultBOFactory.createResultBO(type);
		Mono<ClientResponse> res = post(valueInput, path);

		return toResponseResult(Response, res);
	}

	public Mono<Response> putRequestObject(String path, String valueInput, ResultType type) throws IOException {
		Response Response = ResultBOFactory.createResultBO(type);
		Mono<ClientResponse> res = put(valueInput, path);

		return toResponseResult(Response, res);
	}

	public Mono<Response> deleteRequest(String path, ResultType type) throws IOException {
		Response Response = ResultBOFactory.createResultBO(type);
		Mono<ClientResponse> res = delete(path);

		return toResponseResult(Response, res);
	}

	public Mono<Response> patchRequest(String path, Object nsd, ResultType type) throws IOException {
		Response Response = ResultBOFactory.createResultBO(type);
		Mono<ClientResponse> res = patch(path, nsd);

		return toResponseResult(Response, res);
	}

	public Mono<Response> downloadFileRequest(String path) throws IOException {
		// https://juejin.im/post/5a62f17cf265da3e51333205
		return null;
	}

	public Mono<Response> uploadFileRequest(String path, FilePart file, ResultType type) throws IOException {
		Response Response = ResultBOFactory.createResultBO(type);
		Mono<ClientResponse> res = upload(path, file);

		return toResponseResult(Response, res);
	}

	private Mono<ClientResponse> post(String valueInput, String path) {
		// create headers for request
		Consumer<HttpHeaders> consumerHeaders = fillDataToHeaders();

		// post request to second server
		Mono<ClientResponse> res = webClient.post()
											.uri(path)
											.contentType(MediaType.APPLICATION_JSON)
											.accept(MediaType.APPLICATION_JSON)
											.headers(consumerHeaders)
											.syncBody(valueInput)
											.exchange();

		return res;
	}

	private Mono<ClientResponse> get(String valueInput, String path) {
		// create headers for request
		Consumer<HttpHeaders> consumerHeaders = fillDataToHeaders();

		// send get request to viettel server
		Mono<ClientResponse> res = webClient.get()
											.uri(path)
											.accept(MediaType.APPLICATION_JSON)
											.headers(consumerHeaders)
											.exchange();

		return res;
	}

	private Mono<ClientResponse> put(String path, String valueInput) {
		// create headers for request
		Consumer<HttpHeaders> consumerHeaders = fillDataToHeaders();

		// send put request to second server
		Mono<ClientResponse> res = webClient.put()
											.uri(path)
											.contentType(MediaType.APPLICATION_JSON)
											.accept(MediaType.APPLICATION_JSON)
											.headers(consumerHeaders)
											.syncBody(valueInput)
											.exchange();

		return res;
	}

	private Mono<ClientResponse> delete(String path) {
		// create headers for request
		Consumer<HttpHeaders> consumerHeaders = fillDataToHeaders();

		// send get request to other server
		Mono<ClientResponse> res = webClient.delete()
											.uri(path)
											.accept(MediaType.APPLICATION_JSON)
											.headers(consumerHeaders)
											.exchange();

		return res;
	}

	private Mono<ClientResponse> patch(String path, Object nsd) {
		// create headers for request
		Consumer<HttpHeaders> consumerHeaders = fillDataToHeaders();

		// send patch request to second server
		Mono<ClientResponse> res = webClient.patch()
											.uri(path)
											.contentType(MediaType.APPLICATION_JSON)
											.accept(MediaType.APPLICATION_JSON)
											.headers(consumerHeaders)
											.syncBody(nsd)
											.exchange();

		return res;
	}

	private Mono<ClientResponse> upload(String path, FilePart file) throws IOException {
		// create headers for request
		Consumer<HttpHeaders> consumerHeaders = fillDataToHeaders();

		// prepare for file to upload
		File clientFile = new File(path);
		if (!clientFile.exists())
			clientFile.mkdirs();

		clientFile = new File("./path-download-folder" + "/" + file.filename());
		clientFile.createNewFile();

		file.transferTo(clientFile);
		Resource resource = new FileSystemResource(clientFile);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("filePart", resource);

		// send upload request
		Mono<ClientResponse> res = webClient.post()
									.uri(path)
									.contentType(MediaType.MULTIPART_FORM_DATA)
									.headers(consumerHeaders)
									.body(BodyInserters.fromMultipartData(body))
									.exchange();

		return res;
	}

	private Mono<String> download(String path, String valueInput) {
		return Mono.empty();
	}

	private Mono<Response> toResponseResult(Response Response, Mono<ClientResponse> res) {
		Mono<Response> result = res.flatMap(clientResponse -> {
			HttpStatus status = clientResponse.statusCode();
			Mono<String> respBackendVt = clientResponse.bodyToMono(String.class).switchIfEmpty(Mono.just(""));

			Mono<Response> resultBOMono = respBackendVt.flatMap(item -> {
				Object trulyResponseJson = null;
				JSONObject jsonError = null;

				if (status != HttpStatus.OK) {
					jsonError = JsonSimpleUtils.toJsonObject(item);
				} else {
					trulyResponseJson = Response.toJsonObject(item);
				}

				ErrHttpCode errMessCode = Constants.getErrHttpCode();
				HttpResponseParam httpParam = new HttpResponseParam(trulyResponseJson, jsonError, errMessCode);
				IHttpResponse httpResponse = responseHttp.get(status);
				if (httpResponse != null) {
					return Mono.just(httpResponse.toResultData(Response, httpParam));
				}

				// if not found http response, return default value
				Response.setError(true);
				Response.setErrorCode(Constants.N_OK);
				Response.setMessage("False!");

				return Mono.just(Response);
			});

			return resultBOMono;
		});

		return result;
	}

	private Consumer<HttpHeaders> fillDataToHeaders() {
		String basicAuth = getSession();
		MultiValueMap<String, String> multiValueMap = getValueForHeaderParameters();

		Consumer<HttpHeaders> consumerHeaders = httpHeaders -> {
			httpHeaders.add(HttpHeaders.AUTHORIZATION, basicAuth);
			httpHeaders.addAll(multiValueMap);
		};

		return consumerHeaders;
	}

	private String getSession() {
		String basicAuth = "";
		if (StringUtils.isNotEmpty(getSessionToken())) {
			basicAuth = getSessionToken();
		}

		return basicAuth;
	}

	private MultiValueMap<String, String> getValueForHeaderParameters() {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		HashMap<String, String> keyValueHeader = this.getValueHeaders();
		
		for (Map.Entry<String, String> item : keyValueHeader.entrySet()) {
			multiValueMap.put(item.getKey(), Collections.singletonList(item.getValue().toString()));
		}

		return multiValueMap;
	}

	private HashMap<String, String> getValueHeaders() {
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("", "");
		header.put("", "");

		return header;
	}

	private String getSessionToken() {
		return "";
	}
}
