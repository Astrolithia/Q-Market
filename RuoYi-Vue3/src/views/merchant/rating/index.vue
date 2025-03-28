<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商家名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入商家名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商家等级" prop="level">
        <el-input
          v-model="queryParams.level"
          placeholder="请输入商家等级"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评分" prop="score">
        <el-input
          v-model="queryParams.score"
          placeholder="请输入评分"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['merchant:rating:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['merchant:rating:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['merchant:rating:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['merchant:rating:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ratingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商家ID" align="center" prop="id" />
      <el-table-column label="商家名称" align="center" prop="name" />
      <el-table-column label="商家编码" align="center" prop="code" />
      <el-table-column label="商家等级" align="center" prop="level" />
      <el-table-column label="评分" align="center" prop="score" />
      <el-table-column label="订单完成率(%)" align="center" prop="completionRate" />
      <el-table-column label="平均发货时间(小时)" align="center" prop="avgShippingTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['merchant:rating:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['merchant:rating:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改商家评级对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="ratingRef" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Rating">
import { listRating, getRating, delRating, addRating, updateRating } from "@/api/merchant/rating";

const { proxy } = getCurrentInstance();

const ratingList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: null,
    level: null,
    score: null,
  },
  rules: {
    name: [
      { required: true, message: "商家名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询商家评级列表 */
function getList() {
  loading.value = true;
  listRating(queryParams.value).then(response => {
    ratingList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    name: null,
    code: null,
    contactPerson: null,
    contactPhone: null,
    email: null,
    address: null,
    businessLicense: null,
    licenseNumber: null,
    legalRepresentative: null,
    category: null,
    profitRatio: null,
    account: null,
    password: null,
    status: null,
    level: null,
    score: null,
    createTime: null,
    applyTime: null,
    auditTime: null,
    auditor: null,
    auditStatus: null,
    rejectReason: null,
    updateTime: null,
    createBy: null,
    updateBy: null,
    deliveryRate: null,
    onTimeRate: null,
    completionRate: null,
    serviceScore: null,
    logisticsScore: null,
    productScore: null,
    ratingCount: null,
    levelUpdateTime: null,
    avgShippingTime: null
  };
  proxy.resetForm("ratingRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加商家评级";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getRating(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改商家评级";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["ratingRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateRating(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addRating(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除商家评级编号为"' + _ids + '"的数据项？').then(function() {
    return delRating(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('merchant/rating/export', {
    ...queryParams.value
  }, `rating_${new Date().getTime()}.xlsx`)
}

getList();
</script>
