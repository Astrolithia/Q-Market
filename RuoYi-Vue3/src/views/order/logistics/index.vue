<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="配送方式" prop="shippingMethod">
        <el-input
          v-model="queryParams.shippingMethod"
          placeholder="请输入配送方式"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物流公司" prop="logisticsCompany">
        <el-input
          v-model="queryParams.logisticsCompany"
          placeholder="请输入物流公司"
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
          v-hasPermi="['order:logistics:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['order:logistics:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['order:logistics:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['order:logistics:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="logisticsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="物流ID" align="center" prop="id" />
      <el-table-column label="订单ID" align="center" prop="orderId" />
      <el-table-column label="订单编号" align="center" prop="orderNo" />
      <el-table-column label="配送方式" align="center" prop="shippingMethod" />
      <el-table-column label="物流公司" align="center" prop="logisticsCompany" />
      <el-table-column label="物流单号" align="center" prop="logisticsNo" />
      <el-table-column label="发货时间" align="center" prop="shippingTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.shippingTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="送达时间" align="center" prop="deliveryTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.deliveryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="物流状态：0待发货，1已发货，2已签收" align="center" prop="logisticsStatus" />
      <el-table-column label="物流信息" align="center" prop="logisticsInfo" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['order:logistics:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['order:logistics:remove']">删除</el-button>
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

    <!-- 添加或修改订单物流对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="logisticsRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单ID" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单ID" />
        </el-form-item>
        <el-form-item label="订单编号" prop="orderNo">
          <el-input v-model="form.orderNo" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="配送方式" prop="shippingMethod">
          <el-input v-model="form.shippingMethod" placeholder="请输入配送方式" />
        </el-form-item>
        <el-form-item label="物流公司" prop="logisticsCompany">
          <el-input v-model="form.logisticsCompany" placeholder="请输入物流公司" />
        </el-form-item>
        <el-form-item label="物流单号" prop="logisticsNo">
          <el-input v-model="form.logisticsNo" placeholder="请输入物流单号" />
        </el-form-item>
        <el-form-item label="发货时间" prop="shippingTime">
          <el-date-picker clearable
            v-model="form.shippingTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择发货时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="送达时间" prop="deliveryTime">
          <el-date-picker clearable
            v-model="form.deliveryTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择送达时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="物流信息" prop="logisticsInfo">
          <el-input v-model="form.logisticsInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
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

<script setup name="Logistics">
import { listLogistics, getLogistics, delLogistics, addLogistics, updateLogistics } from "@/api/order/logistics";

const { proxy } = getCurrentInstance();

const logisticsList = ref([]);
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
    shippingMethod: null,
    logisticsCompany: null,
    logisticsStatus: null,
  },
  rules: {
    orderId: [
      { required: true, message: "订单ID不能为空", trigger: "blur" }
    ],
    orderNo: [
      { required: true, message: "订单编号不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询订单物流列表 */
function getList() {
  loading.value = true;
  listLogistics(queryParams.value).then(response => {
    logisticsList.value = response.rows;
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
    orderId: null,
    orderNo: null,
    shippingMethod: null,
    logisticsCompany: null,
    logisticsNo: null,
    shippingTime: null,
    deliveryTime: null,
    logisticsStatus: null,
    logisticsInfo: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("logisticsRef");
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
  title.value = "添加订单物流";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getLogistics(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改订单物流";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["logisticsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateLogistics(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addLogistics(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除订单物流编号为"' + _ids + '"的数据项？').then(function() {
    return delLogistics(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('order/logistics/export', {
    ...queryParams.value
  }, `logistics_${new Date().getTime()}.xlsx`)
}

getList();
</script>
