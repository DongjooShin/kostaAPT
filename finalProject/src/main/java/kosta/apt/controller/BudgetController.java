package kosta.apt.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.oreilly.servlet.MultipartFilter;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kosta.apt.domain.management.Budget;
import kosta.apt.service.BudgetService;

@Controller
public class BudgetController {
	private BudgetService budgetService;

	@Autowired
	public void setBudgetService(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	@RequestMapping("/budgetInsertForm")
	public String budgetInsertForm() {
		return "/budget/budgetInsert";
	}

	@RequestMapping("/budgetInsert")
	public String budgetInsert(Budget budget) {
		System.out.println(budget.toString());
		budget.setApt_APTGNo(1);
		budget.setB_fileName("no file");
		budgetService.budgetInsert(budget);
		return "/main";
	}

	@RequestMapping("/budgetExcelInsert")
	public String budgetExcelInsert(HttpServletRequest request) throws Exception {
		String uploadPath = request.getRealPath("");
		uploadPath += "\\WEB-INF\\views\\budget\\budgetExcelUpload";
		int size = 20 * 1024 * 1024;
		System.out.println(uploadPath);

		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8",
				new DefaultFileRenamePolicy());
		String b_fileName = multi.getFilesystemName("b_fileName");
		String b_date = multi.getParameter("b_date");
		System.out.println(b_fileName);

		budgetService.budgetExcelInsert(multi);

		String filePath = uploadPath + "\\" + b_fileName;

		// 엑셀데이터 입력받을 객체 생성
		Budget budget = new Budget();
		String[] str = null;

		XSSFWorkbook workbook;
		XSSFSheet sheet;
		XSSFRow row;
		XSSFCell cell;
		InputStream input;
		try {
			input = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(input);
			sheet = workbook.getSheetAt(0); // 시트 가져오기
			int rows = sheet.getPhysicalNumberOfRows(); // Row 갯수 가져오기

			String b_clean = null, b_general = null, b_maintain = null, b_lifMaintain = null, b_security = null,
					b_foodWaste = null, b_commission = null, b_meeting = null, b_liftElectric = null,
					b_appropriation = null, b_publicElectric = null, b_fireInsurance = null, b_disinfection = null,
					b_TVFee = null;
			int i = 1;
			row = sheet.getRow(i); // row 가져오기
			System.out.println(row + "row");
			if (row != null) {
				// && !"".equals(row.getCell(0).getStringCellValue())) {
				
				cell = row.getCell(0);
				System.out.println(cell + "셀셀");
				b_clean = cell + "";
				System.out.println(b_clean);
				cell = row.getCell(1);
				b_general = cell + "";
				System.out.println(b_general);
				cell = row.getCell(2);
				b_maintain = cell + "";
				cell = row.getCell(3);
				b_lifMaintain = cell + "";
				cell = row.getCell(4);
				b_security = cell + "";
				cell = row.getCell(5);
				b_foodWaste = cell + "";
				cell = row.getCell(6);
				b_commission = cell + "";
				cell = row.getCell(7);
				b_meeting = cell + "";
				cell = row.getCell(8);
				b_liftElectric = cell + "";
				cell = row.getCell(9);
				b_appropriation = cell + "";
				cell = row.getCell(10);
				b_publicElectric = cell + "";
				cell = row.getCell(11);
				b_fireInsurance = cell + "";
				cell = row.getCell(12);
				b_disinfection = cell + "";
				cell = row.getCell(13);
				b_TVFee = cell + "";

				str = b_clean.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_clean(Integer.parseInt(str[0]));

				str = b_general.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_general(Integer.parseInt(str[0]));

				str = b_maintain.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_maintain(Integer.parseInt(str[0]));

				str = b_lifMaintain.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_liftMaintain(Integer.parseInt(str[0]));

				str = b_security.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_security(Integer.parseInt(str[0]));

				str = b_foodWaste.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_foodWaste(Integer.parseInt(str[0]));

				str = b_fireInsurance.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_fireInsurance(Integer.parseInt(str[0]));

				str = b_commission.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_commission(Integer.parseInt(str[0]));

				str = b_meeting.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_meeting(Integer.parseInt(str[0]));

				str = b_publicElectric.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_publicElectric(Integer.parseInt(str[0]));

				str = b_liftElectric.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_liftElectric(Integer.parseInt(str[0]));

				str = b_TVFee.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_TVFee(Integer.parseInt(str[0]));

				str = b_disinfection.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_disinfection(Integer.parseInt(str[0]));

				str = b_appropriation.split("\\.");
				System.out.println(str[0] + "배열");
				budget.setB_appropriation(Integer.parseInt(str[0]));

				// 파일 업로드
				if (multi.getFilesystemName("b_fileName") != null) {
					String f_name = multi.getFilesystemName("b_fileName"); // 파일이름을
																			// 구해줌
					budget.setB_fileName(f_name);
				} else {
					budget.setB_fileName("");
				}
				
				budget.setB_date(b_date);
				budget.setApt_APTGNo(1); // 아파트 그룹
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		System.out.println(budget.toString() + "1");
		budgetService.budgetExcelInsert(budget);
		
		return "/main";
	}
	
	@RequestMapping("/budgetList")
	public String budgetList(Model model){
		List<Budget> list = budgetService.selectBudgetList();
		System.out.println(list.size());
		model.addAttribute("budget1", list.get(0));
		model.addAttribute("budget2", list.get(1));
		model.addAttribute("budget3", list.get(2));
		model.addAttribute("budget4", list.get(3));
		model.addAttribute("budget5", list.get(4)); 
		return "/budget/budgetList";
	}


}
