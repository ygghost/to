package nc.ui.to.report.pub;

import java.awt.Container;

import javax.swing.Action;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.pub.smart.tracedata.ITraceDataOperator;
import nc.pub.smart.tracedata.TraceDataInterface;
import nc.pub.smart.tracedata.TraceDataParam;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.billgraph.SourceBillFlowDlg;
import nc.ui.trade.billgraph.billflow.control.DefaultBillGraphListener;
import nc.vo.ml.NCLangRes4VoTransl;

/**
 * 内部交易模块报表联查 单据id为 cbillid 单据号为 vbillcode 单据类型 vbilltypecode 可以使用此类
 * 
 * @date 2018-01-29
 * @author yegz
 */
@SuppressWarnings("restriction")
public class TOReportLinkQuery implements ITraceDataOperator,
		TraceDataInterface {

	private static final String BILL_FINDER_CLASSNAME = "nc.impl.pubapp.linkquery.BillTypeSetBillFinder";

	@Override
	public Action[] ctreateExtensionActions() {
		return null;
	}

	@Override
	public com.ufida.report.free.userdef.IMenuActionInfo getMenuItemInfo() {
		com.ufida.report.free.userdef.DefaultMenu dmenu = new com.ufida.report.free.userdef.DefaultMenu(
				"linkBill", NCLangRes.getInstance().getStrByID("4006005_0",
						"04006005-0008")/* 联查单据 */);
		return dmenu;
	}

	@Override
	public ITraceDataOperator[] provideTraceDataOperator() {
		return new TOReportLinkQuery[] { new TOReportLinkQuery() };
	}

	@Override
	public void traceData(Container container, TraceDataParam param) {
		if (container == null || param == null) {
			String hint = NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"4006005_0", "04006005-0000")/* @res "请选择行！" */;
			NCLangRes nclang = NCLangRes.getInstance();
			MessageDialog.showHintDlg(container,
					nclang.getStrByID("4006005_0", "04006005-0009")/* 提示 */,
					hint);
			return;
		}
		String billID = (String) param.getRowData().getData("cbillid");
		String billCode = (String) param.getRowData().getData("vbillcode");
		String cbilltype = (String) param.getRowData().getData("cbilltypecode");

		SourceBillFlowDlg dlg = new SourceBillFlowDlg(WorkbenchEnvironment
				.getInstance().getWorkbench(),
				TOReportLinkQuery.BILL_FINDER_CLASSNAME, cbilltype, billID,
				billCode);

		// 设置监听器
		dlg.setBillGraphListener(new DefaultBillGraphListener());
		dlg.showModal();
	}

}
