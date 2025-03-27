<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="优惠券名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入优惠券名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="已领取数量" prop="used">
        <el-input
          v-model="queryParams.used"
          placeholder="请输入已领取数量"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商家ID" prop="merchantId">
        <el-input
          v-model="queryParams.merchantId"
          placeholder="请输入商家ID"
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
          v-hasPermi="['marketing:coupon:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketing:coupon:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketing:coupon:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['marketing:coupon:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="couponList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="优惠券ID" align="center" prop="id" />
      <el-table-column label="优惠券名称" align="center" prop="name" />
      <el-table-column label="优惠券类型：1满减券，2折扣券，3直减券" align="center" prop="type" />
      <el-table-column label="优惠券面值" align="center" prop="value" />
      <el-table-column label="使用门槛，0表示无门槛" align="center" prop="minPoint" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发行量，0表示不限量" align="center" prop="total" />
      <el-table-column label="已领取数量" align="center" prop="used" />
      <el-table-column label="每人限领数量" align="center" prop="receiveCount" />
      <el-table-column label="状态：0禁用，1启用" align="center" prop="status" />
      <el-table-column label="使用说明" align="center" prop="description" />
      <el-table-column label="商家ID" align="center" prop="merchantId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['marketing:coupon:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['marketing:coupon:remove']">删除</el-button>
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

    <!-- 添加或修改优惠券管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="couponRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="优惠券名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入优惠券名称" />
        </el-form-item>
        <el-form-item label="优惠券面值" prop="value">
          <el-input v-model="form.value" placeholder="请输入优惠券面值" />
        </el-form-item>
        <el-form-item label="使用门槛，0表示无门槛" prop="minPoint">
          <el-input v-model="form.minPoint" placeholder="请输入使用门槛，0表示无门槛" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发行量，0表示不限量" prop="total">
          <el-input v-model="form.total" placeholder="请输入发行量，0表示不限量" />
        </el-form-item>
        <el-form-item label="已领取数量" prop="used">
          <el-input v-model="form.used" placeholder="请输入已领取数量" />
        </el-form-item>
        <el-form-item label="每人限领数量" prop="receiveCount">
          <el-input v-model="form.receiveCount" placeholder="请输入每人限领数量" />
        </el-form-item>
        <el-form-item label="使用说明" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="商家ID" prop="merchantId">
          <el-input v-model="form.merchantId" placeholder="请输入商家ID" />
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

<script setup name="Coupon">
import { listCoupon, getCoupon, delCoupon, addCoupon, updateCoupon } from "@/api/marketing/coupon";

const { proxy } = getCurrentInstance();

const couponList = ref([]);
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
    type: null,
    startTime: null,
    endTime: null,
    used: null,
    status: null,
    merchantId: null,
  },
  rules: {
    name: [
      { required: true, message: "优惠券名称不能为空", trigger: "blur" }
    ],
    type: [
      { required: true, message: "优惠券类型：1满减券，2折扣券，3直减券不能为空", trigger: "change" }
    ],
    value: [
      { required: true, message: "优惠券面值不能为空", trigger: "blur" }
    ],
    startTime: [
      { required: true, message: "开始时间不能为空", trigger: "blur" }
    ],
    endTime: [
      { required: true, message: "结束时间不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询优惠券管理列表 */
function getList() {
  loading.value = true;
  listCoupon(queryParams.value).then(response => {
    couponList.value = response.rows;
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
    type: null,
    value: null,
    minPoint: null,
    startTime: null,
    endTime: null,
    total: null,
    used: null,
    receiveCount: null,
    status: null,
    description: null,
    merchantId: null,
    createTime: null,
    updateTime: null,
    createBy: null,
    updateBy: null
  };
  proxy.resetForm("couponRef");
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
  title.value = "添加优惠券管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getCoupon(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改优惠券管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["couponRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCoupon(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCoupon(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除优惠券管理编号为"' + _ids + '"的数据项？').then(function() {
    return delCoupon(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketing/coupon/export', {
    ...queryParams.value
  }, `coupon_${new Date().getTime()}.xlsx`)
}

getList();
</script>
