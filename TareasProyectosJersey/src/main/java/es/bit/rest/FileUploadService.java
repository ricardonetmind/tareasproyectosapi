package es.bit.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/file")
public class FileUploadService {

	private static final String UPLOAD_PATH = "c:/temp/uploads/";

	@POST
	@Path("/upload")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response uploadPdfFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
			while ((read = fileInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		return Response.ok("Data uploaded successfully !!").build();
	}

	@POST
	@Path("/download")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Consumes("application/x-www-form-urlencoded")
	public Response downloadFileWithPost(@FormParam("file") String file) {
		File fileDownload = new File(UPLOAD_PATH + File.separator + file);
		ResponseBuilder response = Response.ok((Object) fileDownload);
		response.header("Content-Disposition", "attachment;filename=" + file);
		return response.build();
	}

	@GET
	@Path("/download")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadFileWithGet(@QueryParam("file") String file) {
		File fileDownload = new File(UPLOAD_PATH + File.separator + file);
		ResponseBuilder response = Response.ok((Object) fileDownload);
		response.header("Content-Disposition", "attachment;filename=" + file);
		return response.build();
	}

}
