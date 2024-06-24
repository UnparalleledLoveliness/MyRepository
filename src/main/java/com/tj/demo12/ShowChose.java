package com.tj.demo12;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ShowChose {
  public static final String PLUGIN_NAME = "CodeGenerator Plugin";
  private JButton generatorCode;
  private JPanel panel1;
  private JCheckBox fullTask;
  private JCheckBox pojo;
  private JCheckBox bo;
  private JCheckBox dao;
  private JCheckBox IncrementalTask;
  private JCheckBox msgUpdater;
  private JCheckBox reader;
  private JRadioButton isPlain;
  private JRadioButton isList;
  private JTextField textField1;
  private AnActionEvent event;
  private PsiClass psiClass;
  private String packageName;
  private String suffixName;
  private String dbName;
  private String tableName;
  private static final Map<String, String> dbName2Name = new LinkedHashMap<>();

  static {
    dbName2Name.put("\"PkgProductDB\"", "PKG_PRODUCT_DB_MYSQL");
    dbName2Name.put("\"PkgProductMetaDB\"", "PKG_PRODUCT_META_DB");
    dbName2Name.put("\"pkgmarketingdb_W\"", "PKG_MARKETING_DB");
    dbName2Name.put("\"FRTShoppingDB_W\"", "FRT_SHOPPING_DB");
    dbName2Name.put("\"CruiseProdDB\"", "CRUISE_PROD");
    dbName2Name.put("\"TTDPoiDB\"", "POI_DB");
    dbName2Name.put("\"ActProductDB\"", "ACT_PRODUCT_DB");
    dbName2Name.put("\"PkgVBookingDB\"", "PKG_VBOOKING_DB");
    dbName2Name.put("\"OttdGlobalResouceDB\"", "OTTD_GLOBAL_RESOUCE_DB");
    dbName2Name.put("\"TTDVendorDB\"", "TTD_VENDOR_DB");
    dbName2Name.put("\"PkgImageDB_W\"", "PKG_IMAGE_DB");
  }

  public ShowChose() {
    generatorCode.addActionListener(e -> {
      Project project = event.getProject();
      PsiFile file = psiClass.getContainingFile();
      //单选
      if (isPlain.isSelected()) {
        if (bo.isSelected()) {
          createFile(generatorPlainBo(), project, file, suffixName + "Bo.java");
        }

        if (dao.isSelected()) {
          createFile(generatorDbDao(), project, file, suffixName + "DbDao.java");
        }

        if (fullTask.isSelected()) {
          createFile(generatorPlainFullTask(), project, file, suffixName + "FullTask.java");
        }

        if (IncrementalTask.isSelected()) {
          createFile(generatorIncrementalTask(), project, file, suffixName + "IncrementalTask.java");
        }

        if (msgUpdater.isSelected()) {
          createFile(generatorPlainMsgUpdater(), project, file, suffixName + "MsgUpdater.java");
        }

        if (reader.isSelected()) {
          createFile(generatorPlainReader(), project, file, suffixName + "Reader.java");
        }

        if (pojo.isSelected()) {
          createFile(generatorPojo(), project, file, suffixName + "Pojo.java");
        }
      } else if (isList.isSelected()) {
        if (bo.isSelected()) {
          createFile(generatorListBo(), project, file, suffixName + "Bo.java");
        }

        if (dao.isSelected()) {
          createFile(generatorDbDao(), project, file, suffixName + "DbDao.java");
        }

        if (fullTask.isSelected()) {
          createFile(generatorListFullTask(), project, file, suffixName + "FullTask.java");
        }

        if (IncrementalTask.isSelected()) {
          createFile(generatorIncrementalTask(), project, file, suffixName + "IncrementalTask.java");
        }

        if (msgUpdater.isSelected()) {
          createFile(generatorListMsgUpdater(), project, file, suffixName + "MsgUpdater.java");
        }

        if (reader.isSelected()) {
          createFile(generatorListReader(), project, file, suffixName + "Reader.java");
        }

        if (pojo.isSelected()) {
          createFile(generatorPojo(), project, file, suffixName + "Pojo.java");
        }
      } else {
        Messages.showWarningDialog("Please select Plain or List", PLUGIN_NAME);
      }
    });
  }

  public ShowChose(AnActionEvent event) {
    JFrame frame = new JFrame("选择生成文件");
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    frame.setVisible(true);
    ShowChose showChose = new ShowChose();
    showChose.event = event;
    frame.setSize(400, 400);
    frame.setContentPane(showChose.panel1);

    //当前文件
    PsiFile psiFile = event.getData(PlatformDataKeys.PSI_FILE);
    showChose.psiClass = getCurrentClass(psiFile);
    if (showChose.psiClass == null) {
      Messages.showWarningDialog("Please focus on a java file", "CodeGenerator Plugin");
      return;
    }

    PsiMethod[] methods = showChose.psiClass.getMethods();
    for (PsiMethod method : methods) {
      if(method.getName().equals("getIncrementalKey")){
        //拿到method的返回值
        PsiField[] fields = PsiTreeUtil.getChildrenOfType(method, PsiField.class);
        for (PsiField field : fields) {
          System.out.println(field.getName());
        }
      }
    }
    PsiJavaFile javaFile = (PsiJavaFile) showChose.psiClass.getContainingFile();
    showChose.packageName = javaFile.getPackageName();

    PsiAnnotation database = showChose.psiClass.getAnnotation("com.ctrip.platform.dal.dao.annotation.Database");
    PsiAnnotation table = showChose.psiClass.getAnnotation("javax.persistence.Table");
    if (database != null) {
      showChose.dbName = database.findAttributeValue("name").getText();
    }

    if (table != null) {
      showChose.tableName = table.findAttributeValue("name").getText();
    }

    String name = showChose.psiClass.getName();
    if (name == null) {
      Messages.showWarningDialog("Please focus on a java file", "CodeGenerator Plugin");
      return;
    }

    showChose.suffixName = name.replace("DbPojo", "");
  }

  private void createFile(String text, Project project, PsiFile file, String fileName) {
    WriteCommandAction.runWriteCommandAction(project, () -> {
      //将s新生成文件
      PsiFile newFile = file.getContainingDirectory().createFile(fileName);
      try {
        newFile.getVirtualFile().setBinaryContent(text.getBytes());
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
  }

  private String generatorPlainBo() {
    return "package " + packageName + ";\n\n" +
        "public class " + suffixName + "Bo extends BizPlainBo<" + suffixName + "Pojo, " + suffixName + "DbPojo> {\n" +
        "  public " + suffixName + "Bo() {\n" +
        "    super(" + suffixName + "DbPojo.class, " + suffixName + "Pojo.class, " + suffixName + "Reader\n" +
        "        .KEY_SUFFIX, true, " + suffixName + "Reader.CLUSTER_NAME);\n" +
        "  }\n" +
        "}\n";
  }

  private String generatorListBo() {
    return "package " + packageName + ";\n\n" +
        "public class " + suffixName + "Bo extends BizId2ListBo<" + suffixName + "Pojo, " + suffixName + "DbPojo> {\n" +
        "  public " + suffixName + "Bo() {\n" +
        "    super(" + suffixName + "DbPojo.class, " + suffixName + "Pojo.class, " + suffixName + "Reader\n" +
        "        .KEY_SUFFIX, true, " + suffixName + "Reader.CLUSTER_NAME);\n" +
        "  }\n" +
        "}\n";
  }

  private String generatorDbDao() {
    return "package " + packageName + ";\n\n" +
        "import com.ctrip.frt.common.AbstractDbDaoV2;\n" +
        "import com.ctrip.frt.product.job.Databases;\n" +
        "import com.ctrip.frt.product.job.DbDaoConfig;\n" +
        "\n" +
        "import java.sql.SQLException;\n" +
        "\n" +
        "class " + suffixName + "DbDao extends AbstractDbDaoV2<" + suffixName + "DbPojo> {\n" +
        "  static final DbDaoConfig dbConfig = new DbDaoConfig(Databases." + dbName2Name.get(dbName) + "\n" +
        "      .logicName(), " + tableName + ");\n" +
        "\n" +
        "  " + suffixName + "DbDao() throws SQLException {\n" +
        "    super(dbConfig.getDatabaseName(), " + suffixName + "DbPojo.class, true);\n" +
        "  }\n" +
        "}\n";
  }

  private String generatorPlainFullTask() {
    return "package " + packageName + ";\n\n" +
        "import com.ctrip.frt.common.task.FullTask;\n" +
        "\n" +
        "import java.sql.SQLException;\n" +
        "\n" +
        "public class " + suffixName + "FullTask extends FullTask {\n" +
        "  private " + suffixName + "DbDao dao;\n" +
        "  private " + suffixName + "Bo bo;\n" +
        "  private " + suffixName + "Processor processor;\n" +
        "\n" +
        "  /** Constructor. */\n" +
        "  public " + suffixName + "FullTask() throws SQLException {\n" +
        "    dao = new " + suffixName + "DbDao();\n" +
        "    bo = new " + suffixName + "Bo();\n" +
        "    processor = new " + suffixName + "Processor();\n" +
        "  }\n" +
        "\n" +
        "  @Override\n" +
        "  protected void doRun() throws InterruptedException {\n" +
        "    processor.run();\n" +
        "  }\n" +
        "\n" +
        "  private class " + suffixName + "Processor extends PlainProcessor<Number, " + suffixName + "DbPojo> {\n" +
        "    " + suffixName + "Processor() {\n" +
        "      super(dao, bo, " + suffixName + "Reader.CLUSTER_NAME);\n" +
        "    }\n" +
        "  }\n" +
        "}\n";
  }

  private String generatorListFullTask() {
    return "package " + packageName + ";\n\n" +
        "import com.ctrip.frt.common.task.FullTask;\n" +
        "\n" +
        "import java.sql.SQLException;\n" +
        "\n" +
        "public class " + suffixName + "FullTask extends FullTask {\n" +
        "  private final " + suffixName + "DbDao dao;\n" +
        "  private final " + suffixName + "Bo bo;\n" +
        "  private final " + suffixName + "Processor processor;\n" +
        "\n" +
        "  /**\n" +
        "   * Constructor\n" +
        "   */\n" +
        "  public " + suffixName + "FullTask() throws SQLException {\n" +
        "    this.dao = new " + suffixName + "DbDao();\n" +
        "    this.bo = new " + suffixName + "Bo();\n" +
        "    processor = new " + suffixName + "Processor();\n" +
        "  }\n" +
        "\n" +
        "  @Override\n" +
        "  protected void doRun() throws InterruptedException {\n" +
        "    processor.run();\n" +
        "  }\n" +
        "\n" +
        "  private final class " + suffixName + "Processor extends Id2ListProcessor<Number, " + suffixName + "DbPojo> {\n" +
        "    private " + suffixName + "Processor() {\n" +
        "      super(dao, bo, " + suffixName + "Reader.CLUSTER_NAME);\n" +
        "    }\n" +
        "  }\n" +
        "}\n";
  }

  private String generatorIncrementalTask() {
    return "package " + packageName + ";\n\n" +
        "import com.ctrip.frt.common.task.IncrementalTask;\n" +
        "\n" +
        "import java.sql.SQLException;\n" +
        "\n" +
        "public class " + suffixName + "IncrementalTask extends IncrementalTask {\n" +
        "  private " + suffixName + "DbDao dao;\n" +
        "  private " + suffixName + "Bo bo;\n" +
        "\n" +
        "  public " + suffixName + "IncrementalTask() throws SQLException {\n" +
        "    dao = new " + suffixName + "DbDao();\n" +
        "    bo = new " + suffixName + "Bo();\n" +
        "  }\n" +
        "\n" +
        "  @Override\n" +
        "  protected void doRun() throws InterruptedException {\n" +
        "    new GeneralProcessor<>(dao, bo).run();\n" +
        "  }\n" +
        "}\n";
  }

  private String generatorPlainMsgUpdater() {
    return "package " + packageName + ";\n\n" +
        "import com.ctrip.frt.product.job.message.update.PlainMsgUpdater;\n" +
        "\n" +
        "import java.util.List;\n" +
        "\n" +
        "public class " + suffixName + "MsgUpdater extends PlainMsgUpdater<" + suffixName + "DbPojo> {\n" +
        "  public " + suffixName + "MsgUpdater() {\n" +
        "    super(" + suffixName + "DbPojo.class);\n" +
        "  }\n" +
        "\n" +
        "  @Override\n" +
        "  public String getDatabaseName() {\n" +
        "    return " + suffixName + "DbDao.dbConfig.getDatabaseName();\n" +
        "  }\n" +
        "\n" +
        "  @Override\n" +
        "  public List<String> getTableNames() {\n" +
        "    return " + suffixName + "DbDao.dbConfig.getTableNames();\n" +
        "  }\n" +
        "\n" +
        "  @Override\n" +
        "  protected void process(List<" + suffixName + "DbPojo> pojos) throws InterruptedException {\n" +
        "    new " + suffixName + "Bo().process(pojos);\n" +
        "  }\n" +
        "}\n";
  }

  private String generatorListMsgUpdater() {
    return "package " + packageName + ";\n\n" +
        "import com.ctrip.frt.product.job.message.update.Id2ListMsgUpdater;\n" +
        "\n" +
        "import java.sql.SQLException;\n" +
        "import java.util.List;\n" +
        "\n" +
        "public class " + suffixName + "MsgUpdater extends Id2ListMsgUpdater<" + suffixName + "DbPojo> {\n" +
        "  public " + suffixName + "MsgUpdater() throws SQLException {\n" +
        "    super(" + suffixName + "DbPojo.class, new " + suffixName + "DbDao());\n" +
        "  }\n" +
        "\n" +
        "  @Override\n" +
        "  protected void process(List<" + suffixName + "DbPojo> pojos) throws InterruptedException {\n" +
        "    new " + suffixName + "Bo().process(pojos);\n" +
        "  }\n" +
        "\n" +
        "  @Override\n" +
        "  public String getDatabaseName() {\n" +
        "    return " + suffixName + "DbDao.dbConfig.getDatabaseName();\n" +
        "  }\n" +
        "\n" +
        "  @Override\n" +
        "  public List<String> getTableNames() {\n" +
        "    return " + suffixName + "DbDao.dbConfig.getTableNames();\n" +
        "  }\n" +
        "}\n";
  }

  private String generatorPojo() {
    String result = "package " + packageName + ";\n\n" +
        "import lombok.Data;\n" +
        "\n" +
        "@Data\n" +
        "public class " + suffixName + "Pojo {\n";

    PsiField[] fields = psiClass.getFields();
    for (PsiField field : fields) {
      String fieldName = field.getName();
      String fieldType = field.getType().getPresentableText();
      result += "  private " + fieldType + " " + fieldName + ";\n";
    }
    result += "}\n";
    return result;
  }

  private String generatorPlainReader() {
    return "package " + packageName + ";\n\n" +
        "import com.ctrip.frt.common.reader.PlainDbReader;\n" +
        "import com.ctrip.frt.common.reader.PlainReader;\n" +
        "import com.ctrip.frt.common.redis.PlainRedisReader;\n" +
        "import com.ctrip.frt.common.redis.RedisException;\n" +
        "import com.ctrip.frt.product.job.ModuleEnum;\n" +
        "\n" +
        "import java.sql.SQLException;\n" +
        "import java.util.Collection;\n" +
        "import java.util.List;\n" +
        "import java.util.Map;\n" +
        "\n" +
        "public class " + suffixName + "Reader {\n" +
        "  static final String KEY_SUFFIX = ModuleEnum." + suffixName + ".redisKeySuffix();\n" +
        "  static final String CLUSTER_NAME = ModuleEnum." + suffixName + ".clusterName();\n" +
        "  private static PlainReader<" + suffixName + "DbPojo, " + suffixName + "Pojo> reader;\n" +
        "\n" +
        "  /**\n" +
        "   * @throws SQLException if failed.\n" +
        "   */\n" +
        "  public static void init() throws SQLException {\n" +
        "    PlainRedisReader<Number, " + suffixName + "Pojo> redisReader =\n" +
        "        new PlainRedisReader<>(" + suffixName + "Pojo.class, KEY_SUFFIX, CLUSTER_NAME);\n" +
        "    PlainDbReader<" + suffixName + "DbPojo, " + suffixName + "Pojo> dbReader =\n" +
        "        new PlainDbReader<>(new " + suffixName + "DbDao(), new " + suffixName + "Bo());\n" +
        "    reader = new PlainReader<>(redisReader, dbReader);\n" +
        "  }\n" +
        "\n" +
        "  public static " + suffixName + "Pojo read(Long id, int mode)\n" +
        "      throws RedisException, SQLException {\n" +
        "    return reader.read(productId, true, mode);\n" +
        "  }\n" +
        "\n" +
        "  public static Map<Number, " + suffixName + "Pojo> readMap(Collection<Long> ids, int mode)\n" +
        "      throws RedisException, SQLException {\n" +
        "    return reader.readMap(productIds, true, mode);\n" +
        "  }\n" +
        "}\n";
  }

  private String generatorListReader() {
    return "package " + packageName + ";\n\n" +
        "import com.ctrip.frt.common.reader.Id2ListDbReader;\n" +
        "import com.ctrip.frt.common.reader.Id2ListReader;\n" +
        "import com.ctrip.frt.common.redis.Id2ListRedisReader;\n" +
        "import com.ctrip.frt.common.redis.RedisException;\n" +
        "import com.ctrip.frt.product.job.ModuleEnum;\n" +
        "\n" +
        "import java.sql.SQLException;\n" +
        "import java.util.Collection;\n" +
        "import java.util.List;\n" +
        "import java.util.Map;\n" +
        "\n" +
        "public class " + suffixName + "Reader {\n" +
        "  static final String KEY_SUFFIX = ModuleEnum." + suffixName + ".redisKeySuffix();\n" +
        "  static final String CLUSTER_NAME = ModuleEnum." + suffixName + ".clusterName();\n" +
        "  private static Id2ListReader<" + suffixName + "DbPojo, " + suffixName + "Pojo> reader;\n" +
        "\n" +
        "  /**\n" +
        "   * init\n" +
        "   */\n" +
        "  public static void init() throws SQLException {\n" +
        "    Id2ListRedisReader<Number, " + suffixName + "Pojo> redisReader =\n" +
        "        new Id2ListRedisReader<>(" + suffixName + "Pojo.class, KEY_SUFFIX, CLUSTER_NAME);\n" +
        "    Id2ListDbReader<" + suffixName + "DbPojo, " + suffixName + "Pojo> dbReader =\n" +
        "        new Id2ListDbReader<>(new " + suffixName + "DbDao(), new " + suffixName + "Bo());\n" +
        "    reader = new Id2ListReader<>(redisReader, dbReader);\n" +
        "  }\n" +
        "\n" +
        "  public static List<" + suffixName + "Pojo> read(Long brandId, int mode) throws RedisException,\n" +
        "      SQLException {\n" +
        "    return reader.read(brandId, true, mode);\n" +
        "  }\n" +
        "\n" +
        "  public static Map<Number, List<" + suffixName + "Pojo>> readMap(\n" +
        "      Collection<? extends Number> brandIds, int mode)\n" +
        "      throws RedisException, SQLException {\n" +
        "    return reader.readMap(brandIds, true, mode);\n" +
        "  }\n" +
        "}\n";
  }

  private PsiClass getCurrentClass(PsiFile psiFile) {
    if (!(psiFile instanceof PsiJavaFile)) {
      Messages.showWarningDialog("Please focus on a java file", PLUGIN_NAME);
      return null;
    }

    PsiTreeUtil.getChildrenOfTypeAsList(psiFile, PsiClass.class);
    List<PsiClass> psiClassList = PsiTreeUtil.getChildrenOfTypeAsList(psiFile, PsiClass.class);
    if (psiClassList.isEmpty()) {
      Messages.showWarningDialog("Cannot find any java class", PLUGIN_NAME);
      return null;
    }
    //只聚焦当前类
    return psiClassList.get(0);
  }
}
