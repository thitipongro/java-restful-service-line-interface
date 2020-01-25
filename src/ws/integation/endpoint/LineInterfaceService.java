package ws.integation.endpoint;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import ws.integation.dao.LineInterfaceDao;
import ws.integation.model.Message;
import ws.integation.model.RequestLine;
import ws.integation.model.ResponseLine;

/**
 * @author Thitipong Roongprasert
 * @date 1 ม.ค. 2563
 * @version 1.0
 */

@Path("LineInterface")
public class LineInterfaceService {	
	Message<String> ms = new Message<>();
	@POST
	@Path("checkCustomerName")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response checkCustomerName(RequestLine dataReq) {
		try {
			ResponseLine data = LineInterfaceDao.checkCustomerName(dataReq);
			String body = new Gson().toJson(data);
			return Response.status(200).entity(body).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
		} catch (Exception e) {
			ms.setMsg(e.getMessage());
			ms.setStatus(-1);
			String body = new Gson().toJson(ms);
			return Response.status(200).entity(body).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
		}
	}

	@POST
	@Path("insertCustomerDetail")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response insertCustomerDetail(RequestLine dataReq) {
		try {
			ResponseLine data = LineInterfaceDao.insertCustomerDetail(dataReq);
			String body = new Gson().toJson(data);
			return Response.status(200).entity(body).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
		} catch (Exception e) {
			ms.setMsg(e.getMessage());
			ms.setStatus(-1);
			String body = new Gson().toJson(ms);
			return Response.status(200).entity(body).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
		}
	}

	@POST
	@Path("updateCustomerGovId")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateCustomerGovId(RequestLine dataReq) {
		try {
			ResponseLine data = LineInterfaceDao.updateCustomerGovId(dataReq);
			String body = new Gson().toJson(data);
			return Response.status(200).entity(body).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
		} catch (Exception e) {
			ms.setMsg(e.getMessage());
			ms.setStatus(-1);
			String body = new Gson().toJson(ms);
			return Response.status(200).entity(body).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
		}
	}

	@POST
	@Path("checkCustomerGovId")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response checkCustomerGovId(RequestLine dataReq) {
		try {
			ResponseLine data = LineInterfaceDao.checkCustomerGovId(dataReq);
			String body = new Gson().toJson(data);
			return Response.status(200).entity(body).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
		} catch (Exception e) {
			ms.setMsg(e.getMessage());
			ms.setStatus(-1);
			String body = new Gson().toJson(ms);
			return Response.status(200).entity(body).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
		}
	}
}

